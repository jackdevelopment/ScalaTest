package idv.jack.thread.test

object ScalaMain {
  
  def main(args: Array[String]) {
    (new NetworkService(2020, 5)).run
    
  }
  
}