package scalaBasics.absoluteBasics

object Expressions extends App {

  val x = 5 + 10
  println(x)

  println(x == 3) // false

  println(x != 7) // true

  println(!(2 == x))

  var y = 4
  y += 3
  println(y) // also works with -= *= /=

  var z = 10
  z -= 4
  println(z) // 6

  // IF expression
  val someCondition = true
  val conditionedValue = if(someCondition) 10 else 5 // IF EXPRESSION
  println(conditionedValue)
  println(if(someCondition) 10 else 5)
  println(2 + 3)

  // loops
  var i = 0
  while(i < 10) {
    println(i)
    i += 1
  }

  val aWeirdValue = (y = 3)

  val codeBlock = {
    val a = 5
    val b = a + 1

    if (b > 5) "hello" else "goodbye"
  }




}
