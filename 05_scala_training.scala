import scala.util.Try
object ScalaTraining {
  //There are no checked exceptions in scala like in Java. Checked
  //exceptions are checked at compile time. Inspiration was so programmers
  //had to think about exception but can easily result in
  //messy error handling code.

  //In scala, no need to tell compiler you're throwing an exception
  case class IllegalUserException(m: String) extends Exception(m)
  case class User(name: String)

  def findUser(): User = {
    throw new IllegalUserException("Could not find User")
  }

  def upsertUser = {
     try {
       findUser()
     } catch {
       case e: IllegalUserException => println(e.getMessage)
     }
  }

  def upsertUserTry = Try {
    findUser()
  }

}
