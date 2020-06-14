import botkop.{numsca => ns}
import ns.Tensor

trait ActivationFunction {

  def apply(x : Tensor): Tensor
  def backward(dA : Tensor, z : Tensor) : Tensor
}

object ReLU extends ActivationFunction {
  override def apply(x: Tensor): Tensor = ns.maximum(x, 0)

  override def backward(dA: Tensor, z: Tensor): Tensor = {
    var dZ = ns.copy(dA)
    dZ(z <= 0) := 0
    dZ
  }
}

object Sigmoid extends ActivationFunction {
  override def apply(x: Tensor): Tensor = 1 / (1 + ns.exp(x))

  override def backward(dA: Tensor, z: Tensor): Tensor = dA - z
}


