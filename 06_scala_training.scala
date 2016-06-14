object ScalaTraining {

  // here we are passing in a fn ...Also called "lambdas" in other languages. To
  // be technically correct, a higher order function is a function that takes
  // function values as parameters. Map is commonly used instead of loops
  def map[T, U](lst: List[T], fn: T => U): List[U] = {
    def helper(lst: List[T], fn: T => U, accum: List[U]): List[U] = lst match {
      case h::t => helper(t, fn, fn(h) :: accum)
      case _ => accum
    }
    helper(lst, fn, List.empty[U]) reverse
  }

  def partialMap = map(List(1,2,3), _: Int => String)

  def cloooosure = {

    // we know that in terms of laziness, a def is the laziest as 
    // evaluation is at the time of invocation. calling closure with an arg
    // will show that scala supports closures
    def x = 1000
    (z: Int) => k + x
  }

  // Scala supports currying out of the box: "A curried function is one where multiple arguments 
  // are described by a series of one-argument functions"
  def chefCurry(numThrees: Int)(numTwos: Int)(numBlocks: Int): Option[String] = {
    if (numThrees > 10 && numTwos > 10 && numBlocks > 10) return Some("Triple Double") else None
  }
 
}
