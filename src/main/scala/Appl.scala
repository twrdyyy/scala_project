import scala.collection.mutable
import botkop.{numsca => ns}
import ns.Tensor

object Appl {
  def main(args: Array[String]): Unit = {
//    val client = new Client
//    val epochs : Int = 100
//    for (_ <- 1 to epochs){
//      var envData : String = client.sendAction("random")
//      println(envData)
//    }
//    client.sendAction("kill")
    val model = new Model(Array("64 relu", "10 sigmoid"), input_size = 2)
    val x = Tensor(1, 2)
    val y_hat = model.predict(x)
  }
}
