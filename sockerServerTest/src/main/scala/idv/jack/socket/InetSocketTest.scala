package idv.jack.socket

import java.net._
import java.nio.channels._
import java.nio.channels.{Selector => NSelector}
import java.nio._
import java.util._

object InetSocketTest {
  
  def main(args:Array[String]){
    val socketAddress = new InetSocketAddress("localhost", 1234)
    val serverChannel = ServerSocketChannel.open() 
    serverChannel.socket.bind(socketAddress)
    serverChannel.configureBlocking(false);
    val nioSelector = NSelector.open();
    serverChannel.register(nioSelector, SelectionKey.OP_ACCEPT);
   
    
    while(true) {
      nioSelector.select();
      val readyKeys = nioSelector.selectedKeys();
      val iterator = readyKeys.iterator()
      
      while(iterator.hasNext()){
          val key = iterator.next();
          iterator.remove();
          
          if(key.isAcceptable()){
            var server = key.channel().asInstanceOf[ServerSocketChannel]
            val client = server.accept();
            
            client.configureBlocking(false);
            
            val clientKey = client.register(nioSelector, SelectionKey.OP_READ)
            
            val buffer = ByteBuffer.allocate(1024)
            
            clientKey.attach(buffer)
          }else if(key.isReadable()){
            val client = key.channel().asInstanceOf[SocketChannel];
            val output = key.attachment().asInstanceOf[ByteBuffer]
            client.read(output)
            println("recv:" + output.array())
           // println("recv:" + new String(Arrays.copyOfRange(output.array(), 0, output.limit())))
            val clientKey = client.register(nioSelector, SelectionKey.OP_WRITE)
          }else if(key.isWritable()){
            val client = key.channel().asInstanceOf[SocketChannel]
            val output = key.attachment().asInstanceOf[ByteBuffer]
            if(output != null){
              output.flip()
              if(output.hasRemaining()){
                client.write(output)
                output.compact()
              }else{
                println("output has not remaining")
              }
            }
              
            
          }
        
        
      }
        
        
        
    }
   // val nioSelector = NSelector.open();
    //serverChannel.register(nioSelector, SelectionKey.OP_ACCEPT);
    
  }
  
}