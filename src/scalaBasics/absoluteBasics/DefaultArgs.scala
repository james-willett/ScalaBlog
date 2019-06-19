package scalaBasics.absoluteBasics

object DefaultArgs extends App {

  def tailRecursiveFactorial(n: Int, acc: Int = 1): Int = {
    if (n <= 1) acc
    else tailRecursiveFactorial(n - 1, n * acc)
  }

  val fact10 = tailRecursiveFactorial(10, 1)
  val fact5 = tailRecursiveFactorial(5, 1)

  val fact8 = tailRecursiveFactorial(8)

  val fact15 = tailRecursiveFactorial(15,2)

  def savePicture(format: String, width: Int, height: Int): Unit = println("saving picture")

  savePicture("jpg", 800, 600)

  def savePicture2(format: String = "jpg", width: Int, height: Int): Unit = println("saving picture")

//  savePicture2(800, 600)

  def savePicture3(format: String = "jpg", width: Int = 1200, height: Int = 1000): Unit = println("saving picture")

//  savePicture3(800)

  savePicture3(width = 800)

  savePicture3(height = 500, format = "bmp", width = 300)
}
