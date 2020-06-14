import botkop.{numsca => ns}
import ns.Tensor

class Model (parameters : Array[String], input_size : Int){

  var prev_layer_input : Int = input_size
  var loss_function: MeasurementFunction = _
  var accuracy_function: MeasurementFunction = _
  var lr: Float = _

  var layers: Array[Layer] = for (layer_params <- parameters) yield {
    val Array(n_neurons, activation) = layer_params.split(" ")
    val layer = new Layer(n_neurons.toInt, prev_layer_input, activation)
    prev_layer_input = n_neurons.toInt
    layer
  }

  def compile(loss : MeasurementFunction, accuracy : MeasurementFunction, lr : Float): Unit = {
    loss_function = loss
    accuracy_function = accuracy
    this.lr = lr
  }

  def add(layer : Layer) : Unit = {
    layers = layers :+ layer
  }

  def fit(x : Tensor, y : Tensor, epochs : Int) : Array[Array[Float]] = {

    var cost : Array[Float] = Array()
    var accuracy : Array[Float] = Array()

    for (epoch <- 1 to epochs) {

      val y_pred = this.forward(x)

      val loss : Float = loss_function(y_pred, y)
      cost = cost :+ loss

      val score = accuracy_function(y_pred, y)
      accuracy = accuracy :+ score

      backward()

      update_weights()

      println(s"Epoch [$epoch/$epochs] accuracy: $score loss: $loss")
    }

    Array(accuracy, cost)
  }

  def forward(x : Tensor): Tensor = {

    var y_hat = layers.head forward x
    layers.tail foreach( layer => y_hat = layer forward y_hat)
    y_hat

  }

  def backward(): Unit = {


  }

  def update_weights() : Unit = {

  }

  def predict(x : Tensor): Tensor = forward(x)

  def summary : String = {

    //TODO Model Summary

    "Model summary :)"

  }

}
