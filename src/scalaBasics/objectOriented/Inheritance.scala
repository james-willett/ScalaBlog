package scalaBasics.objectOriented

object Inheritance extends App {

  class Animal {
    def eat = println("I am eating!")
  }

  class Cat extends Animal

  class Animal2 {
    private def eat = println("I am eattng!")
  }

  class Animal3 {
    protected def eat = println("I am eattng!")
  }

  class Cat2 extends Animal3 {
    def burp = {
      eat
      println("Burp! Pardon me!")
    }
  }

  val cat2 = new Cat2
  cat2.burp

  val cat = new Cat
  cat.eat

  // CONSTRUCTORS
  class Person(name: String, age: Int)

  class Adult(name: String, age: Int, idCard:String) extends Person(name, age)

  class Person2(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult2(name: String, age: Int, idCard:String) extends Person2(name)

  // OVERRIDING
  class Dog extends Animal3 {
    override def eat = println("I am a dog eating!")
  }
  val dog = new Dog
  dog.eat

  class Animal4 {
    val creatureType = "wild"
    protected def eat = println("I am eating!")
  }

  class Dog2 extends Animal4 {
    override val creatureType = "domestic"
    override def eat = println("I am a dog eating!")
  }

  val dog2 = new Dog2
  println(dog2.creatureType)

  class Dog3(override val creatureType: String) extends Animal4 {
    override def eat = println("I am a dog eating!")
  }
  val dog3 = new Dog3("puppy")
  println(dog3.creatureType)

  class Dog4(dogType: String) extends Animal4 {
    override val creatureType = dogType
    override def eat = println("I am a dog eating!")
  }

  val unknownAnimal: Animal4 = new Dog4("Boxer")

  class Animal5 {
    val creatureType = "wild"
    def eat = println("I am eating!")
  }

  val unknownAnimal2: Animal5 = new Dog5("Boxer")
  unknownAnimal2.eat

  class Dog5(dogType: String) extends Animal5 {
    override val creatureType = dogType
    override def eat = println("I am a dog eating!")
  }

  class Dog6(dogType: String) extends Animal5 {
    override val creatureType = dogType
    override def eat = {
      super.eat
      println("I am a dog eating!")
    }
  }

  val unknownAnimal3: Animal5 = new Dog6("Boxer")
  unknownAnimal3.eat

  // PREVENTING OVERRIDES
  class Animal6 {
    val creatureType = "wild"
    final def eat = println("I am eating!")
  }

  final class Animal7 {
    val creatureType = "wild"
    def eat = println("I am eating!")
  }

  sealed class Animal8 {
    val creatureType = "wild"
    def eat = println("I am eating!")
  }

}
