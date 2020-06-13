import java.net._
import java.io._
import scala.io._

class Client {

  def run: Unit = {
    val s = new Socket(InetAddress.getByName("localhost"), 50000)
    lazy val in = new BufferedSource(s.getInputStream).getLines()
    val out = new PrintStream(s.getOutputStream)
    out.println("lol")
    out.flush()
    println("Received: " + in.next)
    out.println("")
    out.flush()

    s.close()
  }
}
