package example

object Traits {
    trait MiscTrait {
        val anotherParam: String
    } 
    trait BaseTrait {
        val param0: MiscTrait
        def showAnotherParam: String = param0.anotherParam 
    }
}