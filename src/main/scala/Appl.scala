import scala.collection.mutable
import botkop.{numsca => ns}
import ns.Tensor

import scala.util.Random

object loss extends MeasurementFunction {
  override def apply(y_pred: Tensor, y: Tensor): Float= {
    ns.mean(ns.power(y-y_pred, 2)).data.head.toFloat
  }
}

object accuracy extends MeasurementFunction {
  override def apply(y_pred: Tensor, y: Tensor): Float = {
    1.0f
  }
}

object DeepQLearning{

  val epochs : Int = 1000
  val gamma : Float = 0.9f
  var epsilon: Float = 0.3f
  val epsilonDecay : Float = 0.99f
  var client = new Client
  val r : Random.type = scala.util.Random
  var envData : String = _
  val model : Model = new Model(Array("64 relu",
                                      "2 relu"), 4)
  model.compile(loss = loss , accuracy = accuracy, lr = 0.05f, log = false)
  var random_action = 0
  var model_action = 0
  var left = 0
  var right = 0

  def main(args: Array[String]): Unit = {

    for (epoch <- 1 to epochs) {
      envData = client.sendAction("start")
      val(state, score, done) = parseEnvData(envData)
      val total = play(state, score)
      println(s"Game $epoch played with score $total model to random actions $model_action/$random_action $left - $right")
      model_action = 0
      random_action = 0
      left = 0
      right = 0
      epsilon *= epsilonDecay
      if (epsilon < 0.01) epsilon = 0.01f
    }
    client.sendAction("end")
  }

  @scala.annotation.tailrec
  def play(state : Array[Float], score : Float): Any = {
    var action : String = null
    if (r.nextFloat < epsilon) {
      action = (scala.math.abs(r.nextInt % 2)).toString
      random_action += 1
    }
    else {
      var q_values : ns.Tensor = model.predict(Tensor(state).reshape(4, 1))
      action = ns.argmax(q_values).data.head.toInt.toString
      action match {
        case "1" => right += 1
        case "0" => left += 1
      }
      model_action += 1
    }
    envData = client.sendAction(action)
    val(next_state, reward, is_done) = parseEnvData(envData)
    val total = score + reward
    var q_values : ns.Tensor = model.predict(Tensor(state).reshape(4, 1))
    if (is_done){
      q_values(action.toInt, 0) := reward
      model.fit(ns.Tensor(state).reshape(4, 1), q_values, epochs=10)
      total
    }
    else {
      val q_values_next = model.predict(ns.Tensor(next_state).reshape(4, 1))
      val q_score = reward + gamma * ns.max(q_values_next).data.head
      q_values(action.toInt, 0) := q_score
      model.fit(ns.Tensor(state).reshape(4, 1), q_values, epochs=10)
      play(next_state, total)
    }
  }

  def parseEnvData(envData : String): (Array[Float], Float, Boolean) = {
    val Array(state_s, score_s, done_s) = envData.split(";")
    var done : Boolean = false
    done_s match {
      case "0" => done = false
      case "1" => done = true
    }
    val score : Float = score_s.toFloat
    val state : Array[Float] = for (s <- state_s.split(" ")) yield s.toFloat
    (state, score, done)
  }
}

object StopServer {
  def main(args: Array[String]): Unit = {
    val client = new Client
    client.sendAction("end")
  }
}
