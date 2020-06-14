import botkop.{numsca => ns}
import ns.Tensor

class Layer(neurons : Int, input_size : Int, activation : String, random_init : Boolean = true, bias_init : Float = 0.0f) {

  var A: Tensor = _
  var B: Tensor = ns.full(Array(neurons), bias_init)
  var f : ActivationFunction = _
  var z : ns.Tensor = _

  activation match {
    case "relu" => f = ReLU
    case "sigmoid" => f = Sigmoid
  }

  random_init match {
    case true => A = ns.rand(input_size, neurons)
    case false => A = ns.zeros(input_size, neurons)
  }


  def forward (x : Tensor) : Tensor = {
    z = ns.dot(A, x) + B
    f(ns.copy(z))
  }

  def backward (grad : Tensor, x : Tensor): Array[Tensor] = {
    val Array(_, m) = x.shape
    val dA = (1 / m) * ns.multiply(grad, f(z).T)
    val dB = (1 / m) * ns.sum(grad, axis = 1).T
    Array(dA, dB)
  }

}
