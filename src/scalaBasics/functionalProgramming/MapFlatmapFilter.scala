package scalaBasics.functionalProgramming

object MapFlatmapFilter extends App {

  val list = List(1,2,3)
  println(list)
  println(list.head)
  println(list.tail)

  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  println(list.filter(_ % 2 == 0))

  val toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair))

  val numbers = List(1,2,3,4)
  val chars = List('a', 'b', 'c', 'd')

  val combinations = numbers.flatMap(n => chars.map(c => "" + c + n))
  println(combinations)

  val colors = List("black", "white")
  val combinationsWithColor = numbers.flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + "-" + color)))
  println(combinationsWithColor)

  list.foreach(println)

  val forCombinations = for {
    n <- numbers
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color
  println(forCombinations)

  val forCombinationsGuard = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color

  println(forCombinationsGuard)

  val combinationsWithColorWithFilter = numbers.filter(_ % 2 == 0).flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + "-" + color)))

  for {
    n <- numbers
  } println(n)

  list.map { x =>
    x * 2
  }

}
