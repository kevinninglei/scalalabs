
object ScalaTraining {
  object PageType {
    case class WrongTypeException(message: String) extends Exception(message)

    //A sealed trait can be extended only in the same file as its declaration.
    sealed trait PageType {
      val name: String
      val hasTransition: Boolean
      val description: String
      override def toString = name
    }

    // here we see apply again. So if we use PageType(..) then we can get something
    def apply(name: String) = name match {
      case MenuItems.name => MenuItems
      case IngredientItems.name => IngredientItems
      case Extras.name => Extras
      case _ => sys.error(s"illegal value for status: ${name}")
    }

    // case classes, used for matching
    case object MenuItems extends PageType {
      val name: String = "menu_items"
      val hasTransition: Boolean = true
      val description: String = "Menu items page allows for configurable transition"
    }

    case object IngredientItems extends PageType {
      val name: String = "ingredient_items"
      val hasTransition: Boolean = false
      val description: String = "Ingredient items page disallows configurable transition"
    }

    case object Extras extends PageType {
      val name: String = "extras"
      val hasTransition: Boolean = false
      val description: String = "Extras page disallows configurable transitions"
    }
  }
}

