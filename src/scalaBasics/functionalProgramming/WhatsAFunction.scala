package scalaBasics.functionalProgramming

object WhatsAFunction extends App {

  // 1. In this post we are going to talk about what a function actually is in Scala.
  // The whole purpose of this section, is to use and work with functions are FIRST CLASS ELEMENTS
  // We want to work with FUNCTIONS like we would with plain VALUES... thats the objective
  // The problem though, is that we come from an object oriented world - i.e. everything is an object, or an instance of some kind of class
  // the only way to simulate function programming in Java, was to use classes and instances of those classes

  // 7 So if we have a MyFunction instance called doubler, which is a new MyFunction that transforms from Int to Int.
  // And the override def apply takes an element of type Int and returns an Int, the implement is that the element * 2 is returned
  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  // This is an instance of the little function like class, i.e. the trait MyFunction.
  // But the advantage of Scala (opposed to Java) is that we can actually call doubler of 2, like it were a function
  println(doubler(2))
  // If you run this, doubler will call the apply method, and print the number 4 to the console
  // So doubler, which is an instance of a function like class, can be called like a function

  // 8 The interesting thing, is that Scala supports these function types out of the box
  // The function types are Function1, Function2, Function3....  up to Function22
  // But for Function with 1 parameter and 1 result, this is Function1[A,B] - this is a function type which is by default supported in Scala

  // 9 So for example. if we create a val which is a stringToIntConverter, and we want to it to be a new Function1.
  // Look at the IDE - (SCREENSHOT OF ALL THE FUNCTIONS) , it is suggesting the function types for us
  // We make the Function String and Int, and it works in the same way
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  // 10 Now, we can actually call this String to Int converter, lets say with the string "3" , and then add the number 4
  println(stringToIntConverter("3") + 4)
  // this will return a 7 to the console

  // 11 As mentioned previously, Scala supports these function types up to 22 parameters
  // So for example if we had a function that takes two ints and returns an Int
  // That would be a Function2 , with Int Int Int. 2 ints for the parmaeter type and the final int for the result type
  // the apply method takes 2 parameters, a and b, both int. And the return type is an int. This is a proper function
  val adder = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }
  // 12 If you hover over the adder val in the IDE, you can see the type is (Int, Int) => Int . This is syntactic sugar for Function2
  // You could rewrite the adder val with the correct type like so:
  val adder2: Function2[Int, Int, Int] = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Or if you wanted to rewrite it with syntactic sugar:
  val added3: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }
  // It takes two ints, and returns an Int

  // 13 So we have function types. If we have a Function with two type parameters A and B, and a result parameter R
  // i.e. Function2[A, B, R] , we write this as (A, B) => R ... we will use this annotation a lot more in this blog post series

  // 14 But the elephant in the room, is that All Scala Functions are objects, or instances of classes derived from Function1, Function2 etc.
  // We are laying the groundwork here for Scala as a functional language based on the JVM, which was never really designed with FP in mind, only OO
  // Through a series of syntactic features, that we will explore in this blog post series, we will get to Scala as a truly functional language

  // EXERCISES

  // 15 to cement our understanding, we will go through a few exercises

  // 16 Lets create a function that takes 2 strings and concatenates them
  // We create a function called concatenator, which is of type (String, String => String) - i.e. it takes 2 Strings and returns a String
  // The value is a new Function2 with 3 Strings
  // The implement of the apply method takes 2 strings (a, b) and returns a string, and the implementation concatenates them together
  def concatenator: (String, String) => String = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a + b
  }
  // 17 we can call the method like so:
  println(concatenator("Hello", "Bob"))

  // 18 Next we are going to make some changes to the MyList application that we developed in the previous blog post series
  // We we are going to transform the MyPredicate and MyTransformer into function types

  // 34 For the next exercise, we will define a function that takes an int and returns another function that takes an int and returns an int
  // This is going to be another type of higher order function
  // First we need to decide what type this function is going to take
  // The type is going to be Function1 that takes an Int, and the return type is going to be ANTOHER Function1 that takes an int and this time returns an Int
  // i.e. Function1[Int, Function1[Int, Int]] - this is the final type of our special function

  // 35 lets type is out then:
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }
  // the apply method takes an Int, lets say x, and returns Function1[Int, Int]
  // so because it returns a Function1 with Int and Int, it will return a new Function1 with Int and Int
  // and then the override def apply within THAT will take an int that we will call Y, of type Int and it returns an Int
  // the implementation isn't that important for this example, lets just add the two numbers
  // so because x is defined in the upper function, x is visible inside the function implementation below

  // 36 lets not use this superAdder function. We declare a val adder3 , and we call superAdder(3) for it
  val adder3 = superAdder(3)
  // now adder3 is a new function, the type is Int => Int - i.e. Function1[Int, Int]

  // 37 if we println adder3, with a parameter 4
  println(adder3(4))
  // this will return 7. This is because the implementation of adder 3 is:
    // override def apply(y: Int): Int = x + y ... which adds x (in this case 3) passed to super adder before .... and y which is the actual parameter

  // 38 in the same fashion, we can write superAdder with parameter 3 and then applied straight away with parameter 4
  println(superAdder(3)(4))
  // So superAdder with paramater 3 returns a Function1, and then we can call that with parameter 4

  // 39 This superAdder special function is called a CURRIED FUNCTION
  // curried functions have the property that they can be called with multiple parameter lists, just by their mere definition
  // so a curried function actually receives some kind of parameter and returns ANOTHER function that receives parameters
  // so super adder can be called with multiple parameter lists to get to the final result
  // Curried functions and higher order functions will be showcased more in upcomming blog posts in this series

  // SUMMARY

  // Our objective with functional programming is to use functions as first class elements
  // That is, we want to be able to pass functions as parameters
  // Or return functions as values in results
  // so basically, we want to work with functions as we would work with any other kind of values

  // The problem is that Scala works on top of the JVM, which was never design for functional programming
  // In Java, first class programming elements are instances of classes

  // The only solution that will work for a functional programming language is to turn all Scala functions into instances of special function like types
  // In Scala, there are function traits of up to 22 parameters
  // A typical function trait looks like this:
  // trait Function1[A, B] {
    // def apply(element: A): B

  // We also have syntatic sugar for function types. For example if we had:
  // Function2[Int. String, Int]
  // This can be rewritten as:
  // (Int, String) => Int










}

// 2 So in Java we would write something like this, where we write out a class (Action) and then methods (execute):
class Action {
  def execute(element: Int): String = ???
}

// 3 The way that we would use these things as "functions" would be to instantiate the Action classs. Either anonymously or non-anonymously
// As described in the anousmous classes post, we would instantiate instances of classes and then override the methods on the spot
// The most you could do would be to genericiscise mosdt of the boilerplate so you can use generic types

// 4 for example we could have an abstract type like a trait, it would take type parameters A and B.
// We would say that the method takes an element of type A and returns an element of type B
trait Action2[A, B] {
  def execute(element: A): B
}
// this is at most, what an object oriented language would be able to do
// 5 this is how the JVM is naturally constructed, so Scala had to resort to some clever tricks to make it look like a truly functional language

// 6 A better design would be to rename the trait to MyFunction, and rename the method to apply - which has a special meaning in Scala
trait MyFunction[A, B] {
  def apply(element: A): B
}



