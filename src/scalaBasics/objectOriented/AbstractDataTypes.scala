package scalaBasics.objectOriented

object AbstractDataTypes extends App {

  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    override def eat: Unit = println("I am a Dog eating!")
  }

  // TRAITS

  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    val creatureType: String = "croc"
    def eat: Unit = println("I am a Crocodile eating!")
    def eat(animal: Animal): Unit = println(s"I am a croc and I am eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  trait ColdBlooded

  abstract class Animal2 {
    val creatureType: String = "wild"
    def eat: Unit
  }
  trait Carnivore2 {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }

}
