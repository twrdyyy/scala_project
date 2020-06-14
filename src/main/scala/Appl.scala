import scala.collection.mutable
import botkop.{numsca => ns}
import ns.Tensor

object loss extends MeasurementFunction {
  override def apply(y_pred: Tensor, y: Tensor): Float = {
    ns.sum(ns.abs(y-y_pred)).toFloat
  }
}

object accuracy extends MeasurementFunction {
  override def apply(y_pred: Tensor, y: Tensor): Float = {
    ns.sum(y/y_pred).toFloat
  }
}

object Appl {
  def main(args: Array[String]): Unit = {
//    val client = new Client
//    val epochs : Int = 100
//    for (_ <- 1 to epochs){
//      var envData : String = client.sendAction("random")
//      println(envData)
//    }
//    client.sendAction("kill")

    val model = new Model(Array("8 relu", "1 relu"), input_size = 2)
    model.compile(loss = loss, accuracy = accuracy, lr = 0.1f)
    val x = ns.ones(2, 10)
    val y = ns.zeros(1, 10)
    y(1) := 1
    model.fit(x, y, epochs = 2)
    val y_hat = model.predict(x)
    println(y_hat)

  }
}
