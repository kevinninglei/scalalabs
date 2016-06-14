
object ScalaTraining {
  abstract class ItemContainer {
    var shouldNotReallyUseVars: Int = 5
  }

  class TrashBag extends ItemContainer {
    override def toString: String = "trashbag"

  }

  // val created a read-only accessor
  // numBags is essentially private
  // var is mutable
  class TrashBagWithArgs(numBags: Int, val smellFactor: Int, var shouldBeChanged: Boolean = false) extends ItemContainer {
    override def toString = s"trashbag with ${numBags} bags"

    shouldBeChanged = if (smellFactor > 10) true else false

    def returnNothing: Unit = println("nothing!")

  }

  object TrashBagWithArgs {
    def apply(numBags: Int, smellFactor: Int, shouldBeChanged: Boolean = false) = 
      new TrashBagWithArgs(numBags, smellFactor, shouldBeChanged)

    // no need to explicit return
    def destroyerFunction(t: TrashBagWithArgs) = Unit

    // scalas way of creating an function (an instance)
    val destroyer: TrashBagWithArgs => Unit = destroyerFunction _
  }

}

