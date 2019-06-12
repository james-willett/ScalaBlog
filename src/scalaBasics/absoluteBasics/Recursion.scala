package scalaBasics.absoluteBasics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      println("Calculating factorial of " + n + " - Need to know the factorial of " + (n-1))
      val result = n * factorial(n-1)
      println("Factorial of: " + n)
      result
    }

  println(factorial(5))

 // println(factorial(5000)) // will cause stack overflow !

  def improvedFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x-1, x * accumulator)

    factHelper(n, 1)
  }

  println(improvedFactorial(5))

  println(improvedFactorial(5000))

  // exercises

  def concatString(theText: String, n: Int, acc: String): String =
    if (n <= 0) acc
    else concatString(theText, n - 1, theText + acc)

  println(concatString("hello", 3, ""))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailrec(t: Int, isStillPrime: Boolean): Boolean = {
      println("Current value of isStillPrime: " + isStillPrime)
      println("Current value of t: " + t)
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailrec(t - 1, n % t != 0 && isStillPrime)
    }

    isPrimeTailrec(n / 2, true)
  }

  println(isPrime(29))

  def fibonacci(n: Int): Int = {
    @tailrec
    def fiboTailrec(i: Int, last: Int, nextToLast: Int): Int = {
      println("Current value of i: " + i)
      println("Current value of last Fib number: " + last)
      println("Current value of next to last Fib Number: " + nextToLast)
      if(i >= n) last
      else fiboTailrec(i + 1, last + nextToLast, last)
    }

    if (n <= 2) 1
    else fiboTailrec(2, 1, 1)
  }

  println(fibonacci(7))



}
