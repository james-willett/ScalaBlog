package scalaBasics.objectOriented

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("I am a funny animal")
  }

  println(funnyAnimal.getClass) // returns: "class scalaBasics.objectOriented.AnonymousClasses$$anon$1"

//  Animal {
//    override def eat: Unit = println("I am a funny animal")
//  }

  class AnonymousClasses$$anon$1 extends Animal {
    override def eat: Unit = println("I am a funny animal")
  }

  val funnyAnimal2: Animal = new AnonymousClasses$$anon$1

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

//  val bob = new Person {
//    override def sayHi: Unit = println("Hi, my name is Bob, what's going on?")
//  }

  val bob = new Person("Bob") {
    override def sayHi: Unit = println("Hi, my name is Bob, what's going on?")
  }

  trait Animal2 {
    def eat: Unit
  }

  val predator = new Animal2 {
    override def eat: Unit = println("I am a predator eating")
  }

}
