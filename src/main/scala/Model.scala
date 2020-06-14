import botkop.{numsca => ns}
import ns.Tensor

class Model (parameters : Array[String], input_size : Int){


  var prev_layer_input : Int = input_size

  var layers: Array[Layer] = for (layer_params <- parameters) yield {
    val Array(n_neurons, activation) = layer_params.split(" ")
    val layer = new Layer(n_neurons.toInt, prev_layer_input, activation)
    prev_layer_input = n_neurons.toInt
    layer
  }

  def add(layer : Layer) : Unit = {
    layers = layers :+ layer
  }

  //TODO fit
  def fit(x : Tensor, y : Tensor, x_val : Tensor, y_val : Tensor) : Array[Float] = {
    val cost : Float = 1.5f
    val loss : Float = 2.5f

    Array(cost, loss)
  }

  def forward(x : Tensor): Tensor = {

    var y_hat = layers.head forward x
    layers.tail foreach( layer => y_hat = layer forward y_hat)
    y_hat

  }

  def backward() = 2

  //TODO train

  def predict(x : Tensor): Tensor = forward(x)

  def summary : String = {

    //TODO Model Summary

    "Model summary :)"

  }

}
