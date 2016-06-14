import scala.concurrent.Future
import java.util.Random
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure}

object ScalaTraining {

  object FlakyUsersAPI {
    private val failAfterThreshold: Double = .5
    case class User(name: String, age: Int)
    case class UsersException(message: String) extends Exception(message)   

    def findAll(): Seq[User] = {
      if (new Random().nextDouble() > failAfterThreshold) throw UsersException("Our API is flaky")
      else List(User("bob", 33), User("mary", 24))
    }
  }

  object LatentFuture {
    private val MAX_SECOND = 3

    def apply[T](n: => T) = Future {
      Thread.sleep(1000 * new Random().nextInt(MAX_SECOND));
      n
    }
  }

  object MyUserModel {
    case class MyUser(name: String, age: Int)
    case class MyUserException(message: String) extends Exception(message)
  }

  def registerSimple = LatentFuture { 100 }.onComplete {
    case Success(_) => println("woooo")
    case Failure(ex) => println("noo")
  }

  def registerNonsimply = LatentFuture { FlakyUsersAPI.findAll() }.onComplete {
    case Success(users) => println(users.toString)
    case Failure(ex) => println(s"Exception: ${ex.getMessage}") 
  }

  def recoverBetter = LatentFuture { FlakyUsersAPI.findAll() }
    .map { _.map { user => MyUserModel.MyUser(user.name, user.age )}}
    .recover {
      case FlakyUsersAPI.UsersException(message) =>
        throw MyUserModel.MyUserException("An issue occured attempting to connect to API")
    }

  def registerRecovered = recoverBetter.onComplete {
    case Success(users) => println(users.toString)
    case Failure(ex) => println(ex.getMessage)  
  }

  // Future.sequence is simply a way to transoform a List(Future(1), Future(2)...) to Future(List(1,2,3...))

  

}