package scalaBasics.objectOriented

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: MyTransformer[A, B]): MyList[B]

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]

  def filter(predicate: MyPredicate[A]): MyList[A]

 def ++[B >: A](list: MyList[B]): MyList[B]
}

// 6 First we will convert this to a case object
case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

// 7 We also convert the Cons class
case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def printElements: String = {
    if(t.isEmpty) "" + h
    else h + " " + t.printElements
  }

  def filter(predicate: MyPredicate[A]): MyList[A] = {
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
    new Cons(transformer.transform(h), t.map(transformer))
  }

 def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
    transformer.transform(h) ++ t.flatMap(transformer)
  }
}

trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(elem: A): B
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
  println(listOfIntegers3.map(new MyTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  })).toString

  println(listOfIntegers3.filter(new MyPredicate[Int] {
    override def test(elem: Int): Boolean = elem % 2 == 0
  })).toString

  // 9 So lets clone this list of integers with the same values:
  val listOfIntegers4: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val clonelistOfIntegers4: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))

  // 10 this would return true
  println(listOfIntegers4 == clonelistOfIntegers4)


  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))

  println((listOfIntegers4 ++ anotherListOfIntegers).toString)

  println(listOfIntegers4.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(elem: Int): MyList[Int] = new Cons(elem, new Cons(elem + 1, Empty))
  }).toString)

}