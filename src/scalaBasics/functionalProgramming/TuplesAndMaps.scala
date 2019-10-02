package scalaBasics.functionalProgramming

object TuplesAndMaps extends App {

  val aTuple = new Tuple2(2, "hello, Scala")

  val aTuple2 = Tuple2(2, "hello, Scala")

  val aTuple3 = (2, "hello, Scala")

  println(aTuple._1)

  println(aTuple.copy(_2 = "hello, James"))

  println(aTuple.swap)

  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("James", 555), ("Bob", 666))

  val phonebook2 = Map(("James", 555), "Bob" -> 666)

  println(phonebook)

  println(phonebook.contains("James"))

  println(phonebook.apply("James"))

  println(phonebook("James"))

//  println(phonebook("Ted")) // throws an exception

  val phonebook3 = Map(("James", 555), "Bob" -> 666).withDefaultValue(-1)

  val newPairing = "Mary" -> 678

  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2)) // returns: Map(james -> 555, bob -> 666)

  println(phonebook.filterKeys(x => x.startsWith("J")))

  println(phonebook.filterKeys(_.startsWith("J")))

  println(phonebook.mapValues(number => number * 10))

  println(phonebook.mapValues(number => "0845-" + number )) // returns: Map(James -> 0845-555, Bob -> 0845-666)

  println(phonebook.toList) // returns: List((James,555), (Bob,666))

  println(List(("Daniel", 555)).toMap) // returns: Map(Daniel -> 555)

  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

}
