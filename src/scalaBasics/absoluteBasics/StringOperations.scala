package scalaBasics.absoluteBasics

object StringOperations extends App {

  val str: String = "Hi, my name is James"

  // Java String Operators

  println(str.charAt(4))

  println(str.substring(4,11))

  println(str.split(" ").toList)

  println(str.startsWith("Hi,"))

  println(str.replace(" ", "-"))

  println(str.toLowerCase())
  println(str.toUpperCase())

  println(str.length())
  println(str.length)

  // Scala String Operators

  val aNumberString = "43"
  val aNumber = aNumberString.toInt

  println('a' +: aNumberString :+ 'z')

  println(str.reverse)

  println(str.take(6))

  val name = "James"
  val age = 21
  val message = s"Hello, my name is $name and I am $age years old"

  val anotherMessage = s"Hello, my name is $name and I will soon be ${age+1} years old"
  println(anotherMessage)

  val speed = 3.4f
  val phrase = f"$name is running at $speed%2.2f miles per hour"
  println(phrase)

  val x = 1.1f
//  val someString = f"$x%3d"

  println(raw"This is a \n newline")

  val escaped = "This is a \n newline"
  println(raw"$escaped")




}
