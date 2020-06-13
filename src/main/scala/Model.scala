import botkop.{numsca => ns}
import ns.Tensor

class Model (parameters : Array[String]){

  var layers: Array[Layer] = for (layer_params <- parameters) yield {
    val Array(n_neurons, activation) = layer_params.split(" ")
    new Layer(n_neurons, activation)
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

  //TODO train

  //TODO predict

  def summary : String = {

    //TODO Model Summary

    "Model summary :)"

  }

}
