package scalaBasics.functionalProgramming

object AnonymousFunctions extends App {

  val doubler = new Function1[Int, Int] {
    override def apply(x: Int) = x * 2
  }

  val doubler2 = (x: Int) => x * 2

  val doubler3: Int => Int = (x: Int) => x * 2

  val doubler4: Int => Int = x => x * 2

  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  val adder2 = (a: Int, b: Int) => a + b

  val justDoSomething = () => 3

  val justDoSomething2: () => Int = () => 3

  println(justDoSomething)
  println(justDoSomething())

  val stringToInt = { (str: String) =>
    str.toInt
  }

  val niceIncrementer: Int => Int = (x: Int) => x + 1

  val niceIncrementer2: Int => Int = _ + 1

  val niceAdder: (Int, Int) => Int = (a, b) => a + b

  val niceAdder2: (Int, Int) => Int = _ + _

  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val superAdder2 = (x: Int) => (y: Int) => x + y

  println(superAdder2(3)(4)) // prints 7

   val lambdaFunction = (name: String, age: Int) => name + " is " + age + " years old"

}
