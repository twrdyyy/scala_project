import botkop.{numsca => ns}
import ns.Tensor

class Layer(neurons : Int, input_size : Int, activation : String, random_init : Boolean = true, bias_init : Float = 0.0f) {

  var A: Tensor = _
  var B: Tensor = ns.full(Array(neurons, 1), bias_init)
  var f : ActivationFunction = _
  var z : ns.Tensor = _
  var prev_z : ns.Tensor = _
  var dA : ns.Tensor = _
  var dB : ns.Tensor = _

  activation match {
    case "relu" => f = ReLU
    case "sigmoid" => f = Sigmoid
  }

  random_init match {
    case true => A = ns.uniform(0, 1, shape = Array(neurons, input_size))
    case false => A = ns.zeros(neurons, input_size)
  }

  def forward (x : Tensor) : Tensor = {
    z = f(ns.dot(A, x) + B)
    z
  }

  def backward (grad : Tensor, x : Tensor): Any = {
    val Array(n, m) = x.shape
    this.dA = (1.0 / m) * ns.dot(grad, prev_z.T)
    this.dB = (1.0 / m) * ns.sum(grad, axis = 1)
  }

}
