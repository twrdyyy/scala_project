import botkop.{numsca => ns}
import ns.Tensor

class Layer(neurons : Int, input_size : Int, activation : String, random_init : Boolean = true, bias_init : Float = 0.0f) {

  var A: Tensor = ns.rand(input_size, neurons)
  var B: Tensor = ns.zeros(neurons, 1)


  //TODO activation function

  //TODO forward

  //TODO backward

}
