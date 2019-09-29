package scalaBasics.objectOriented

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: A => B): MyList[B]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def filter(predicate: A => Boolean): MyList[A]

  def ++[B >: A](list: MyList[B]): MyList[B]

  def foreach(f: A => Unit): Unit

  def sort(compare: (A, A) => Int): MyList[A]

}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = Empty

  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  def foreach(f: Nothing => Unit): Unit = ()

  def sort(compare: (Nothing, Nothing) => Int) = Empty

}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def printElements: String = {
    if(t.isEmpty) "" + h
    else h + " " + t.printElements
  }

  def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  def map[B](transformer: A => B): MyList[B] = {
    new Cons(transformer(h), t.map(transformer))
  }

 def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
    transformer(h) ++ t.flatMap(transformer)
  }

  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A, A) => Int): MyList[A] = {

    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)


  }
}

object ListTest extends App {

  val list = new Cons(1, Empty)
  println(list.head)

  val list2 = new Cons(1, new Cons(2, new Cons(3, Empty)))

  println(list2.tail.head)

  println(list2.add(4).head)

  println(list2.isEmpty)

  println(list2.toString)

  val listOfIntegers: MyList[Int] = Empty
  val listOfStrings: MyList[String] = Empty

  val listOfIntegers2: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfStrings2: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))
  println(listOfIntegers2.toString)
  println(listOfStrings2.toString)

  val listOfIntegers3: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))

  println(listOfIntegers3.map(elem => elem * 2).toString)

  println(listOfIntegers3.map(_ * 2).toString)

  println(listOfIntegers3.filter(elem => elem % 2 == 0).toString)

  println(listOfIntegers3.filter(_ % 2 == 0).toString)


  val listOfIntegers4: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val clonelistOfIntegers4: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))

  println(listOfIntegers4 == clonelistOfIntegers4)

  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))

  println((listOfIntegers4 ++ anotherListOfIntegers).toString)

  println(listOfIntegers4.flatMap(elem => new Cons(elem, new Cons(elem + 1, Empty))).toString)


}