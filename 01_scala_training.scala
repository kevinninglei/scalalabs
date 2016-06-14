
object ScalaTraining {
  abstract class ItemContainer {
    var shouldNotReallyUseVars: Int = 5
  }

  class TrashBag extends ItemContainer {
    override def toString: String = "trashbag"

  }

  class TrashBagWithArgs(numBags: Int) extends ItemContainer {
    override def toString = s"trashbag with ${numBags} bags"

  }

  object TrashBagWithArgs {
    def apply(numBags: Int) = new TrashBagWithArgs(numBags)
  }

}

