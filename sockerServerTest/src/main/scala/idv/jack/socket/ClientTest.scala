package idv.jack.socket
import java.net._
import java.nio.channels._
import java.nio.channels.{Selector => NSelector}
import java.nio._
import java.util._
import java.io._

object ClientTest {
  def main(args:Array[String]){
    val socketAddress = new InetSocketAddress("localhost", 1234)
    val socketChannel = SocketChannel.open() 
    socketChannel.connect(socketAddress)
    
    val writer = new PrintWriter(socketChannel.socket().getOutputStream())
    writer.print("aaaaaa123")
  
  
  }
}