package scalaBasics.functionalProgramming

import scala.util.Random

object Sequences extends App {

  val aSequence = Seq(1,3,2,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2)) // takes the item at index 2 (in this case 3)
  println(aSequence ++ Seq(7,5,6)) // for concatenation
  println(aSequence.sorted) // sorts into order, if the type is by default ordered

  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println) // prints all the numbers from 1 to 10

  (1 to 10).foreach(x => println("Hello"))

  val aList = List(1,2,3)
  val prepended = 42 :: aList
  println(prepended)

  val prepend_append = 42 +: aList :+ 88
  println(prepend_append)

  val apple5 = List.fill(5)("apple")
  println(apple5)

  println(aList.mkString("-"))

  val numbers = Array(1,2,3,4)

  val threeElements = Array.ofDim[Int](3)
  threeElements.foreach(println)

  numbers(2) = 0
  println(numbers.mkString(" "))

  val numbersSeq: Seq[Int] = numbers
  println(numbersSeq)

  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // Vectors vs Lists

  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random

    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt()) // as this random index, put a value of r.nextInt() into the collection
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns // this computes the average time it takes for the collection to be updated

  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  println(getWriteTime(numbersList))
  println(getWriteTime(numbersVector))

}
