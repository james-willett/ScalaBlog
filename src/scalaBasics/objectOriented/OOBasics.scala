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

  // EXERCISE 1 - NOVEL and AUTHOR

  val author = new Writer("Jimmy", "Stevens", 1967)
  val novel = new Novel("My Great Book", 1988, author)

  println(novel.authorAge)

  println(novel.isWrittenBy(author))

  val imposter = new Writer("Jimmy", "Stevens", 1967)
  println(novel.isWrittenBy(imposter))

  val copyOfNovel: Novel = novel.copy(2001)

  // EXERCISE 2 - COUNTER CLASS
  val counter = new Counter(0) // replace with default

  counter.inc.print

  counter.inc.inc.inc.print

  counter.inc2(10).print
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

// EXERCISE 1 - NOVEL and AUTHOR

class Writer(firstName: String, surname: String, val year: Int) {

  def fullName: String = firstName + " " + surname
}

class Novel(name: String, year: Int, author: Writer) {

  def authorAge = year - author.year

  def isWrittenBy(author: Writer) = author == this.author

  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

// EXERCISE 2 - COUNTER CLASS

class Counter(val count: Int) {

  def inc = new Counter(count + 1)
  def dec = new Counter(count - 1)

  def inc(n: Int) = new Counter(count + n)
  def dec(n: Int) = new Counter(count - n)

  def inc2 = new Counter(count + 1) {
    println("incrementing")
    new Counter(count + 1)
  }

  def inc2(n: Int): Counter = {
    if (n <= 0) this
    else inc2.inc2(n-1)
  }

  def print = println(count)
}