package scalaBasics.objectOriented

object Objects extends App {

  object Person {
    val N_EYES = 2
    def canTalk: Boolean = false
  }

  class Person2(val name: String) {

  }

  object Person2 {
    def from(mother: Person2, father: Person2): Person2 = new Person2("Johnny")
    def apply(mother: Person2, father: Person2): Person2 = new Person2("Johnny")
  }

  println(Person.N_EYES)

  println(Person.canTalk)

  val anne = Person
  val bob = Person
  println(anne == bob) // true

  val anne2 = new Person2("Anne")
  val bob2 = new Person2("Bob")
  println(anne2 == bob2) // false

  val johnny = Person2.from(anne2, bob2)

  val johnny2 = Person2.apply(anne2, bob2)

  val johnny3 = Person2(anne2, bob2)


}
