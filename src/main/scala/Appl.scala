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
//    val model = new Model(Array("3 relu", "4 softmax"), input_size = 2)

    val l = new Layer(neurons = 4, input_size = 4, activation = "relu")
    val x = Tensor(1, 2, 3, 4).T
    println(l.forward(x))
    val grad = ns.rand(4, 4)
    val Array(dA, dB) = l.backward(grad, x)
    val lr = 0.001f
    println(l.A)
    println(l.B)
    println(l.A - dA * lr)
    println(l.B - dB * lr)
  }
}
