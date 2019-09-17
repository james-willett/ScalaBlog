package scalaBasics.objectOriented

object Exceptions extends App{

  val x: String = null
  println(x.length) // this will crash with a null pointer exception

  throw new NullPointerException

  val someWeirdValue = throw new NullPointerException

  val someWeirdValue2: String = throw new NullPointerException

  // CATCHING EXCEPTIONS

  def getInt(withExceptions: Boolean): Int = {
    if (withExceptions) throw new RuntimeException("No int to be returned")
    else 43
  }

  try {
    // this is the that might throw an exception
    getInt(true)
  } catch {
    // this code gets executed if the above try block throws an exception
    case e: RuntimeException => println("caught a Runtime exception")
  } finally {
    // this code gets executed no matter what
    println("finally block")
  }

  val potentialFail = try {
    // this is the that might throw an exception
    getInt(true)
  } catch {
    // this code gets executed if the above try block throws an exception
    case e: RuntimeException => println("caught a Runtime exception")
  } finally {
    // this code gets executed no matter what
    println("finally block")
  }

  val potentialFail2 = try {
    // this is the that might throw an exception
    getInt(true)
  } catch {
    // this code gets executed if the above try block throws an exception
    case e: RuntimeException => 44
  } finally {
    // this code gets executed no matter what
    println("finally block")
  }

  // DEFINE YOUR OWN EXCEPTIONS
  class MyException extends Exception
  val exception = new MyException

  throw exception


  // CRASH WITH OUT OF MEMORY
  val array = Array.ofDim(Int.MaxValue)

  // STACK OVERFLOW ERROR
  def infinite: Int = 1 + infinite
  val noLimit = infinite

  // POCKET CALCULATOR
  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y
      result
    }
  }

  println(PocketCalculator.add(Int.MaxValue, 10))

  object PocketCalculator2 {
    def add(x: Int, y: Int) = {
      val result = x + y
      if(x > 0 && y > 0 && result < 0) throw new OverflowException
    }
  }

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by 0")

  object PocketCalculator3 {
    def add(x: Int, y: Int) = {
      val result = x + y
      if(x > 0 && y > 0 && result < 0) throw new OverflowException
      else if(x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }
  }

  // SUBTRACT
  object PocketCalculator4{
    def subtract(x: Int, y: Int) = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }
  }

  // MULTIPLY
  object PocketCalculator5 {
    def multiply(x: Int, y: Int) = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }
  }

  // DIVIDE
  object PocketCalculator6 {
    def divide(x: Int, y: Int) = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

  println(PocketCalculator6.divide(2, 0))
}
