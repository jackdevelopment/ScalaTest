import scala.io.Source

object SourceTest1 {
  def main(args :Array[String]){
      val source = Source.fromFile("/home/user1/myfile.txt", "UTF-8")
      val lineIterator = source.getLines();
      
      for(line <- lineIterator){
        println(line);
      }
      //val mkStr = source.mkString
      //println("MKSTRING:" + mkStr)
      source.close();
  }
  
}