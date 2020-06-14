import botkop.{numsca => ns}
import ns.Tensor

class Layer(neurons : Int, input_size : Int, activation : String, random_init : Boolean = true, bias_init : Float = 0.0f) {

  var A: Tensor = ns.rand(input_size, neurons)
  var B: Tensor = ns.zeros(neurons, 1)
  var f : ActivationFunction = ReLU
  var z : ns.Tensor = ns.zeros(1)

  activation match {
    case "relu" => f = ReLU
    case "sigmoid" => f = Sigmoid
  }

  def forward (x : Tensor) : Tensor = {
    z = ns.dot(A, x) + B
    f(ns.copy(z))
  }

  def backward (grad : Tensor, x : Tensor, y : Tensor): Array[Tensor] = {
    val Array(_, m) = x.shape
    val dA = (1 / m) * ns.multiply(grad, f(z).T)
    val dB = (1 / m) * ns.sum(grad, axis = 1).T
    Array(dA, dB)
  }

}
