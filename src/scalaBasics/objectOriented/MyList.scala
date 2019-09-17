package scalaBasics.objectOriented

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"

  // 20 This is the first element that has broken
//  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  // We can fix this by changing the transformer to a Function1[A, B] ... or to A => B type
  def map[B](transformer: A => B): MyList[B]

  // 21 For the flatMap, this is what we had previously
//  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  // This will change to A => MyList[B] type
  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  // 22 And for filter:
//  def filter(predicate: MyPredicate[A]): MyList[A]
  // this will become A => Boolean , because it returns a Boolean
  def filter(predicate: A => Boolean): MyList[A]
  // suddenly, everything seems to be more functional programming like! Lets keep going through the code

 def ++[B >: A](list: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def printElements: String = ""

  // 23 Next for the map in the Empty object, this was previously
//  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
  // This will become
  def map[B](transformer: Nothing => B): MyList[B] = Empty

  // 24 the flatMap :
//  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
  // this will become:
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  // 25 and for the filter
//  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
  // this will become:
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  // 33 These 3 in particular are called Higher order functions
  // Higher order functions either receive functions as parameters or return other functions as result
  // HOF are critical to FP, because functions are used as first class citizen


  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
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

  // 26 Going down into the Cons class. Previously our filter looked like this:
//  def filter(predicate: MyPredicate[A]): MyList[A] = {
//    if (predicate.test(h)) new Cons(h, t.filter(predicate))
//    else t.filter(predicate)
//  }

  // In the filter, the predicate will become type A => Boolean ,i.e
  // The predicate.test method doesn't make sense anymore. Instead we can call predicate.apply(h) ... or simply predicate with the the apply
  // because we can now call it like a function
  def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }


  // 27 Going down, next is the map function:
//  def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
//    new Cons(transformer.transform(h), t.map(transformer))
//  }

  // The transformer method will become an A => B type
  // And the tranform method that no longer exists, we can call the apply method or simply omit apply and just call transformer
  def map[B](transformer: A => B): MyList[B] = {
    new Cons(transformer(h), t.map(transformer))
  }

 def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  // 28 Finally for flatMap, this was previously:
//  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
//    transformer.transform(h) ++ t.flatMap(transformer)
//  }

  // and it will become:
    def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
      transformer(h) ++ t.flatMap(transformer)
    }
}


// 19 So MyPredicate and MyTransformer as basically function types
// MyPredicate is a function type from T to Boolean , T => Boolean
//trait MyPredicate[-T] {
//  def test(elem: T): Boolean
//}

// And MyTransformer is a function type from A => B
//trait MyTransformer[-A, B] {
//  def transform(elem: A): B
//}
// Now we don't actually need these traits anymore, because we already have function types. So we can delete them from the MyList altogether
// Of course, a bunch of things in our MyList application will now not compile, so lets go through and fix the errors


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
  // 29 Going down to the tests in ListTest, we were previously instantiating MyTransformer and MyPredicate, e.g.:
//  println(listOfIntegers3.map(new MyTransformer[Int, Int] {
//    override def transform(elem: Int): Int = elem * 2
//  })).toString

  // We can replace them simply with Function1, i.e.
  println(listOfIntegers3.map(new Function1[Int, Int] {
    override def apply(elem: Int): Int = elem * 2
  })).toString

  // we also replace the method names that we were overwriting to always be apply

  // 30 MyPredicate[Int] becomes Function1[Int, Boolean]
//  println(listOfIntegers3.filter(new MyPredicate[Int] {
//    override def test(elem: Int): Boolean = elem % 2 == 0
//  })).toString
  println(listOfIntegers3.filter(new Function1[Int, Boolean] {
    override def apply(elem: Int): Boolean = elem % 2 == 0
  })).toString

  val listOfIntegers4: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val clonelistOfIntegers4: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))

  println(listOfIntegers4 == clonelistOfIntegers4)


  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))

  println((listOfIntegers4 ++ anotherListOfIntegers).toString)

  // 31 MyTransformer here:
//  println(listOfIntegers4.flatMap(new MyTransformer[Int, MyList[Int]] {
  ////    override def transform(elem: Int): MyList[Int] = new Cons(elem, new Cons(elem + 1, Empty))
  ////  }).toString)
  // This will become Function1:
    println(listOfIntegers4.flatMap(new Function1[Int, MyList[Int]] {
      override def apply(elem: Int): MyList[Int] = new Cons(elem, new Cons(elem + 1, Empty))
    }).toString)

  // 32 Functions are now being used as First class values in the MyList application


}