package idv.jack.thread.test
import java.net.{Socket, ServerSocket}

import java.util.concurrent.{Executors, ExecutorService}
import java.util.Date

class NetworkService(port: Int, poolSize: Int) {
  val serverSocket = new ServerSocket(port)
  val pool: ExecutorService = Executors.newFixedThreadPool(poolSize)
  
  def run() {
    try {
      while(true){
        //This will block until a connection comes in.
        val socket = serverSocket.accept();
        pool.execute(new Handler(socket))
      }
      
    } finally {
      pool.shutdown();
    }
  }
}
class Handler(socket: Socket) extends Runnable {
  def message = (Thread.currentThread.getName() + "\n").getBytes

  def run() {
    println("connect server:" + new String(message))
    socket.getOutputStream.write(message)
    socket.getOutputStream.close()
  }
  
}