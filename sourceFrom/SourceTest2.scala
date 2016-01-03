import scala.io._;

object SourceTest2 {
  def main(args: Array[String]){
    val source = Source.fromFile("/home/user1/myfile.txt", "UTF-8")
    
    /*val iter = source.buffered
    while(iter.hasNext){
      println(iter.head)
      iter.next()
    }*/
    
    val tokens = source.mkString.split("\\s+")
    val array = for(w <- tokens) yield w.toString()
    //val array = tokens.map(_.toString())
    for(a <- array){
       println(a)
    }
 
    source.close()
  }
  
  
}