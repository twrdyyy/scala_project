
class Model (parameters : Array[String]){


  var layers: Array[Layer] = for (layer_params <- parameters) yield {
    val Array(n_neurons, activation) = layer_params.split(" ")
    new Layer(n_neurons, activation)
  }

  def add(layer : Layer) : Unit = {
    layers = layers :+ layer
  }


  //TODO layers to model

  //TODO fit

  //TODO train

  //TODO predict

  def summary : String = {

    //TODO Model Summary

    "Model summary :)"

  }

}
