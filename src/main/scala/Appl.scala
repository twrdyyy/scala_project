
object Appl {
  def main(args: Array[String]): Unit = {
    val client = new Client
    val epochs : Int = 100

    for (_ <- 1 to epochs){

      var envData : String = client.sendAction("random")
      println(envData)

    }

    client.sendAction("kill")

  }
}
