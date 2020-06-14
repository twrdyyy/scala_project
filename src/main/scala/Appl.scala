import scala.collection.mutable
import botkop.{numsca => ns}
import ns.Tensor

object loss extends MeasurementFunction {
  override def apply(y_pred: Tensor, y: Tensor): Float = {
    ns.sum(y-y_pred).toFloat
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

    val model = new Model(Array("4 relu", "2 sigmoid"), input_size = 2)
    model.compile(loss = loss, accuracy = accuracy, lr = 0.01f)
    val x = ns.rand(10, 2)
    val y = ns.rand(10, 2)
    model.fit(x, y, epochs = 10)
//    val y_hat = model.predict(x)
//    print(y_hat)
  }
}
