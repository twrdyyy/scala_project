import java.net._
import java.io._

class Client (val host : String = "localhost", val port : Int = 50000) {
  def sendAction(action : String): String = {
    val sock = new Socket(InetAddress.getByName(host), port)
    val out = new PrintStream(sock.getOutputStream)
    out.print(action)
    out.flush
    val envData = scala.io.Source.fromInputStream(sock.getInputStream).mkString
    sock.close
    envData
  }
}
