package scalaBasics

package object objectOriented {

  // 11 the name of the file is simply 'package' , again this naming convention is rarely broken
  // 12 another screenshot
  // 13 so a package can only contain one package object, with the same name of the package, in a file called 'package.scala'

  // 14 inside this package object, we can define methods or constants and use them by their simple name inside the rest of the package
  def sayHello: Unit = println("Hello, Scala")
  val SPEED_OF_LIGHT = 299792458

}
