package scalaBasics.objectOriented

object Generics extends App {

  class MyList2[A] {

  }

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  class MyMap[Key, Value]

  trait SomeTrait[A]

  // GENERIC METHODS
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  // VARIANCE PROBLEM
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  class CovariantList[+A]

  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  class InvariantList[A]

  class ContravariantList[-A]

  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  class Trainer[-A]
  val trainerOfCats: Trainer[Cat] = new Trainer[Animal]
  val trainerOfDogs: Trainer[Dog] = new Trainer[Animal]


  // BOUNDED TYPES
  class Cage[A <: Animal](animal: A)

  val cage = new Cage(new Dog)

  class Car
//  val newCage = new Cage(new Car)

  class MyList[+A] {

//    def add(element: A): MyList[A] = ???

    def add[B >: A](element: B): MyList[B] = ???
  }


// SUMMARY

  trait List[T] {
    def add(elem: T)
  }

  object List {
    def single[A](element: A): List[A] = ???
  }

  trait Map[Key, Value] {
    // ...
  }

  // 1. Yes - Covariant
  trait List2[+A]
  // 2. No - Invariant (which is the default)
  trait List3[A]
  // 3. Opposite - contravariant
  trait List4[-A]

}
