package lectures.sec2oop

object V16Inheritance2AbstractTrart extends App {
  // Para ejecutar oprimir [Ctrl + Shift + F10]
  // abstract
  abstract class Animal {
    val creatureType: String = "wild"
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    def eat: Unit = println("guauu guauu")
  }

  // traits (no permite crear instancias)
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc croc croc"                          // sobreescribe de Animal
    def eat: Unit = println("nom.nom.nom" + " (creatureType) : " + creatureType)  // sobreescribe de Animal
    def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")//vieneDeTrait Carnivore
  }

  val dog = new Dog
  print("dog.eat: ")
  dog.eat                                               // viene del padre Animal - lo sobreescribe
  println("dog.creatureType: " + dog.creatureType)      // viene del padre Animal - lo sobreescribe
  println(" ++ +++++ ++ ")

  val croc = new Crocodile
  print("croc.eat: ")
  croc.eat                                              // viene del padre Animal - lo sobreescribe
  println(" ++ +++++ ++ ")
  croc.eat(dog)                                         // viene de Trait Carnivore
  println("croc.preferredMeal: " + croc.preferredMeal)  // viene de Trait Carnivore
  println(" ++ +++++ ++ ")

  // traits vs abstract classes
  // 1 - traits do not have constructor parameters
  // 2 - multiple traits may be inherited by the same class
  // 3 - traits (rasgos) = behavior (describe lo que hace) |  abstract class = "thing" (describe la clase)

  /*
  * scala.Any <- scala.AnyRef (java.lang.Object) {String, List, Set, ...} <- scala.Null {su inst es ref nula} <- scala.Nothing
  * scala.Any <- scala.AnyVal {Int, Unit, Boolean, Float, ...} <- scala.Nothing [ning instancia de nada en absoluto]
  * */

}
