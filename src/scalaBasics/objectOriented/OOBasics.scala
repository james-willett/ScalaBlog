package scalaBasics.objectOriented

object OOBasics extends App {

  val person = new Person

  println(person)

  val person2 = new Person2("Bob", 31)

  val person3 = new Person3("Anne", 43)
  println(person3.age)

  val person4 = new Person4("Steve", 33)
  println(person4.x)

  person4.greet("Andrew")

  person4.greet2("Andrew")

  person4.greet()
}

class Person

class Person2(name: String, age: Int)

class Person3(name: String, val age: Int)

class Person4(name: String, val age: Int) {

  val x = 5

  println(2 + 3)

  def greet(name: String): Unit = println(s"$name says: Hi, $name")

  def greet2(name: String): Unit = println(s"${this.name} says: Hi, $name")

  def greet(): Unit = println(s"Hi, I am $name")

  def this(name: String) = this(name, 0)

  def this() = this("Chris")

}