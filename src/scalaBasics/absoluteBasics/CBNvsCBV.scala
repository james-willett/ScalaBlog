package scalaBasics.absoluteBasics

object CBNvsCBV extends App {


  def calledByValue(x: Long): Unit = {
    println("by value: " + x)
    println("by value: " + x)
  }

  // the arrow means that this parameter will be called by name
  // the arrow makes all the difference in the world and explains the difference in the by name call
  def calledByName(x: => Long): Unit = {
    println("by name: " + x)
    println("by name: " + x)
  }

  // in this call, the exact value of the expression is computed before the function evaluates
  // and the same value is used in the function definition
  // the same value is used everywhere in the function
  // this is probably the style of calling parameters that you used in other languages
  // its as if you put in calledByValue(178327898841203L) ... and that value gets used throughout the definition
  calledByValue(System.nanoTime())


  // in this call, the expression is passed LITERALLY AS IS
  // the expression is evaulated every time
  // so it would like like println("by name: " + System.nanoTime())
  // this is why we see 2 different values in the BY NAME instance
  // this is what the => does... it delays the evaluation of the expression passed as a parameter and is used literally everytime in the function definition
  // this is useful in lazy streams, or things that might fail
  calledByName(System.nanoTime())


  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

 // printFirst(infinite(), 43) - will crash with a stack overflow error

  printFirst(43, infinite()) // the by name parameter delays the evaluation of the expression until its used... and this is never used or evaluated

}
