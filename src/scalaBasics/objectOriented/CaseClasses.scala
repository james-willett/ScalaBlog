package scalaBasics.objectOriented

object CaseClasses extends App {

  // 1 When it comes to case classses, the problem that we want to solve is as follows.
  // Often for lightweight data structures, we need to reimplement all sorts of boilerplate code.
  // For example companion objects, standard methods for serializing and pretty printing like equals, hashCode, toString
  // Case classes are an ideal solution to this problem. They are exceptionally useful shorthand for defining a class and companion object
  // and a lot of sensible defaults in one go. Perfect for lightweight data holding classes with minimum of hassle

  // 2 To create a case class we do is simply:
  case class Person(name: String, age: Int)

  // 3 So the only difference is the keyword CASE, but that keyword is so powerful because it does multiple things.
  // First of all, class parameters are promoted to fields. So we can do this:
  val jim = new Person("Jim", 34)
  println(jim.name)
  // If we didn't inlcude the CASE keyword, the parameter would not have been a field and the IDE would show an error

  // Secondly, a sensible toString for ease of debugging
  println(jim.toString)
  // This returns Person(Jim, 34)
  // If we didn't include the CASE kewyord, then the toString would return something more cryptic like scalaBasics.objectOriented.CaseClasses$Person@7c30a502
  // One other small thing to note, if we println the object jim, it will automatically defer to the toString method
  println(jim)
  // So println(instance) is equal to println(instance.toString) ... this is another form of syntactic sugar

  // Thirdly, equals and hashCode are implemented out of the box. This makes cases classes important for collections
  val jim2 = new Person("Jim", 34)
  println(jim == jim2) // returns true

  // Fourthly , case classes have handy copy methods
  val jim3 = jim.copy(age = 45)
  // We can also specify new parameters to overwrite the existing instance when calling copy (e.g. age 45)
  println(jim3)

  // Fifthly, case classes have companion objects. For example if we do:
   val thePerson = Person
  // Then this is a valid definition, Person is the companion object of this case class
  // Companion objects also have some handy factory methods. For example we can call the default APPLY method on the companion object to create a new instance of Person:
  val mary = Person("Mary", 23)

  // Sixth feature, case classes are serializable. This makes them exceptionally useful when dealing with distributed systems.
  // You can send instances of case classes through the network and in between JVMs. Especially important for Akka - which sends serializable messages through the network

  // Finally, case classes have extractor patterns. This means that case classes can be used in PATTERN MATCHING - one of the most powerful Scala features

  // 4 There is also the concept of a CASE OBJECT
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }
  // case objects have the same properties as case classes, except they don't get companion objects because they ARE their own companion objects

  // 5 To finish this blog, we are going to expand the MyList application that we were developing in the previous post to use Case Classes and Case Objects

  // 8 And with those two case keywords, we have added a lot of power to our MyList application
  // We have implemented EQUALS and HASHCODE out of the box, so I can use the list in collections as well
  // We have made the list serializable, which makes it powerful to use in distributed lectures
  // So if we now look at the ListTests ....

  // 11 In Summary, case classes are a small but very powerful feature of Scala
  // They are a way of defining light data structures with as little boilerplate code as possible, and with a lot of features
  // We get a lot of things out of the box:
  // - Companion objects are already implemented
  // - Sensible methods are included like equals, hashCode and toString
  // - Parameters are auto-promoted to fields, so that we can use them as if they are vals
  // - Cloning is also implemented. Case classes are also serializable and also extractable in pattern matching (to be discussed later)
  // - Case objects are the same as case classes - only they are objects!










}
