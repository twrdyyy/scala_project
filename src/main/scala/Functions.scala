import botkop.{numsca => ns}
import ns.Tensor

trait ActivationFunction {
  def call(x : Tensor): Tensor
  def d(x : Tensor) : Tensor
}

//object ReLU extends ActivationFunction {
//}


