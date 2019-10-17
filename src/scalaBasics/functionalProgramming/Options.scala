package scalaBasics.functionalProgramming

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption) // prints Some(4)

  def unsafeMethod(): String = null

  val result = Some(unsafeMethod())

  val result2 = Option(unsafeMethod())

  println(result2) // returns None

  def backupMethod(): String = "A valid result"

  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod

  println(myFirstOption.isEmpty) // returns false
  println(myFirstOption.get) // this is unsafe, could throw a null pointer exception, do not use this

  println(myFirstOption.map(_ * 2)) // this returns Some(8)

  println(myFirstOption.filter(x => x > 10)) // returns None

  println(myFirstOption.flatMap(x => Option(x * 10))) // returns Some(40)


  val config: Map[String, String] = Map(
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected!!" // connect to some server
  }

  object Connection {
    val random = new Random((System.nanoTime()))

    // the apply method returns a connection or no connection depending on the random choice
    // it simulates a connection or a faulty connection to the server
    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  val host = config.get("host")
  val port = config.get("port")

  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))

  val connectionStatus = connection.map(c => c.connect)

  println(connectionStatus)
  connectionStatus.foreach(println)

  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect

  forConnectionStatus.foreach(println)
}
