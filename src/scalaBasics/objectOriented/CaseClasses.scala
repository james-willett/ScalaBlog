package scalaBasics.objectOriented

object CaseClasses extends App {

  case class Person(name: String, age: Int)

  val jim = new Person("Jim", 34)
  println(jim.name)

  println(jim.toString)

  println(jim)

  val jim2 = new Person("Jim", 34)
  println(jim == jim2) // returns true

  val jim3 = jim.copy(age = 45)
  println(jim3)

  val thePerson = Person

  val mary = Person("Mary", 23)

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }











}
