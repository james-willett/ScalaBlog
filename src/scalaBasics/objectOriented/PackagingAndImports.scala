package scalaBasics.objectOriented

import java.util.Date
import java.sql.Date

object PackagingAndImports extends App {

  // 1. In this blog post we will talk about packages a lot. So what is a package?
  // A package is many definitions that are grouped under the same name. Most of the time, this matches directory structure.

  // 2. As an example, at the top of a Scala file you will simply have something like
  // package scalaBasics.objectedOriented
  // And we can see from the screenshot of the Project directory how this relates

  // 3. Members within a package are visible and accessible by using their simple name, no matter where they are within the package
  // For example they could be in a completely different file, but if that file is in the same package, they can be accessed

  // 4 If you aren't in the package, then you need to import it
  // import someOtherPackage.SomeOtherClass

  // 5 So to use a class or trait or a top level definition by their simple name, you need to be in the same package or you need to import them from the proper package

  // 6 Alternaitvely, if you don't want to import the package, you can supply the full name in the IDE
 // val someVal = new someOtherPackage.someOtherClass
  // this is known as the 'fully qualified name'

  // 7 packages are ordered by hierarchy. it matches the folder structure in the file system

  // PACKAGE OBJECT

  // 8 a package object is a particular feature of Scala
  // this originated from the problem that sometimes we might want to write methods or constants outside of everything else
  // we can now only write classes and traits and objects and access values or constants from them
  // but what about universal constants, or universal methods that reside outside classes
  // so package objects were created for this purpose

  // 9 to create a package object in intellii, we right click on the package that this object should sit in and choose new > package object
  // screenshot of above

  // 10 package objects can only be 1 per package

  // 15 now throughout the rest of the package, we can access these methods:
  sayHello
  println(SPEED_OF_LIGHT)

  // 16 package objects aren't used much in practice, but they are handy if you need them

  // IMPORTS

  // 17 if you are importing two files from the same package, you can put them in curly braces:
  // import someOtherPackage.{SomeOtherClass, SecondClass}

  // 18 or to import everything, simply use an underscore
  // import someOtherPackage._
  // only use this if you really need it, as a best practice

  // NAME ALIASING AT IMPORT

  // 19 you can set aliasing of classes in the import statement like so:
  // import someOtherPackage.{SomeOtherClass, SecondClass => SecondClassAlias }
  // this is useful if you need to import more than one class with the same name from different packages

  // 20 if you imported both of these:
  // import java.util.Date
  // import java.sql.Date

  // and then did:
  val d = new Date
  // the IDE will use the first import statement, which may not be what you wanted

  // 21 If you wanted to use both of these dates in your code, you could either use a fully qualified names (as shown above) or you can use aliasing
  // 22 to do that, for one of the imports, use curly braces and an arrow:
  // import java.sql.{Date => SqlDate}

  // DEFAULT IMPORTS

  // 23 default imports are packages that are automatically imported without you having to do anything
  // examples include java.lang - which contains String, Object, Exception
  // also the top level scala package - which contains Int, Nothing, Function
  // and Scala.PreDef - which containts println, ???

  // SUMMARY

  // we leanred that a package is a group of definitions under the same name
  // the top level package definition allows members to use each other with their simple name
  // to use by a simple name, you need to be in the same package or import the package
  // as a best practice, the package naming conventions shoudl mirror the file structure

  // we saw how we can use the fully qualified name to call a class within a package without explicitly importing it
  // we learned about package objects which hold standalone methods / contants. They can only be one per package and are called 'package'

  // finally we learned about different ways of doing imports, and how to aliasing when importing










}
