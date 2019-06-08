package scalaBasics.absoluteBasics

object Functions extends App {

  def someFunction(a: String, b: Int): String =
    a + " " + b

  println(someFunction("someText", 3))

  def someFunctionWithNoParameters(): Int = 23
  println(someFunctionWithNoParameters)
  println(someFunctionWithNoParameters()) // better

  def someRecursiveFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + someRecursiveFunction(aString, n-1)
  }

  println(someRecursiveFunction("someText", 3))

  def someBigFunction(n: Int): Int = {
    def someSmallFunction(a: Int, b: Int): Int = a + b

    someSmallFunction(n, n-1)
  }

  println(someBigFunction(3))

  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t-1)

    isPrimeUntil(n / 2)
  }

  println(isPrime(5))


}
