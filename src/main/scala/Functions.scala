import botkop.{numsca => ns}
import ns.Tensor

trait ActivationFunction {

  def apply(x : Tensor): Tensor
  def backward(dA : Tensor, z : Tensor) : Tensor
}

trait MeasurementFunction {
  def apply(y_pred : Tensor, y : Tensor) : Float
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

  override def backward(dA: Tensor, z: Tensor): Tensor = {
    val s = apply(z)
    ns.multiply(dA, ns.multiply(s, 1-s))
  }
}


