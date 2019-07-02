package scalaBasics.objectOriented

object MethodNotations extends App {

  class Person(val name: String, faveMovie: String, val age: Int = 0) {

    def likes(movie: String): Boolean = movie == faveMovie

    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def &(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def &(nickname: String): Person = new Person(s"$name ($nickname)", faveMovie)

    def unary_! : String = s"$name, what is going on?"

    def unary_+ : Person = new Person(name, faveMovie, age + 1)

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I like $faveMovie"

    def apply(n: Int): String = s"$name watched $faveMovie $n times"

    def learns(thing: String) = s"$name is learning $thing"
    def learnsScala = this learns "Scala"
  }

  val anne = new Person("Anne", "Oblivion")
  println(anne.likes("Oblivion"))
  println(anne likes "Oblivion")

  val bob = new Person("Bob", "Titantic")
  println(anne.hangOutWith(bob))
  println(anne hangOutWith bob) // prints - Anne is hanging out with Bob

  println(anne & bob)
  println(anne.&(bob))

  println(1 + 2)
  println(1.+(2))

  val x = -1
  val y = 1.unary_-

  println(!anne)
  println(anne.unary_!)

  println(anne.isAlive)
  println(anne isAlive)

  println(anne.apply())
  println(anne())

  println((anne & "the Eagle"))
  println(anne.&("the Eagle"))

  println((+anne).age)
  println(anne.unary_+.age)

  println(anne learnsScala)
  println(anne.learnsScala)

  println(anne(10))
  println(anne.apply(10))

}
