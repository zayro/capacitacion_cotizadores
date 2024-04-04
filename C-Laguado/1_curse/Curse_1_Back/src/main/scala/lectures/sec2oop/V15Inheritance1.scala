package lectures.sec2oop

object V15Inheritance1 extends App {
  // Para ejecutar oprimir [Ctrl + Shift + F10]
  // single class inheritance
  sealed class Animal { // sealed (ser exhaustivo en la jerarquia)(los unicos animales posib gato, perro en ese archivo)
    val creatureType = "wild"
    def eat = println("nomnom" + " (creatureType) : " + creatureType)
  }

  class Cat extends Animal {
    def miau = {
      eat // se llama metodo padre y se imprime 'nomnom'
      println("miau miau miau")
    }
  }
  val cat = new Cat
  cat.miau
  println(" ++ +++++ ++ ")

  class Cat2 extends Animal {
    def miau2 = { // sin llamar metodo padre
      println("miau2 miau2 miau2")
    }
  }
  val cat2 = new Cat2
  cat2.miau2
  println(" ++ +++++ ++ ")


  // constructors
  class PersonClass(name: String, age: Int) {
    def apply(): String = s"Hi, my name is $name and I have $age old years"
    def this(name: String) = this(name, 0)
  }
  class AdultClass(name: String, age: Int, idCard: String) extends PersonClass(name)
  class AdultClass2(name: String, age: Int, idCard: String) extends PersonClass(name, age) {
    def greeting = {
      println(apply() + " { PersonClass(name, age) }") // se llama metodo padre y se imprime 'la cadena interpolada'
      println("greeting world!")
    }
  }

  val person1 = PersonClass("Carlos", 36)
  println("person1.apply() : " + person1.apply())
  val person2 = PersonClass("Laguado", 38)
  println("person2()       : " + person2())

  val person3 = new AdultClass("Eusebio", 34, "id_3")
  println("person3()       : " + person3() + " { PersonClass(name) }")
  val person4 = new AdultClass2("Fernandez", 32, "id_4")
  person4.greeting
  println(" ++ +++++ ++ ")

  // overriding
  class Dog(override val creatureType: String) extends Animal {
    //    override val creatureType = "domestic" // can override in the body or directly in the constructor arguments
    override def eat = { // al colocar 'override' se debe especificar el 'super.'
      super.eat // se llama metodo padre y se imprime 'nomnom'
      println("guau, guau")
    }
  }
  val dog = new Dog("K9") // al tener override sobreescribe el valor del padre en 'creatureType'
  dog.eat
  println("dog.creatureType: " + dog.creatureType)
  println(" ++ +++++ ++ ")

  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("Unknown9")
  unknownAnimal.eat
  println("[polymorphism] [: Animal new Dog(..)] unknownAnimal.creatureType: " + unknownAnimal.creatureType)
  println(" ++ +++++ ++ ")

  val unknownAnimal2: Animal = new Cat()
  unknownAnimal2.eat
  println("[polymorphism] [: Animal new Cat()] unknownAnimal2.creatureType: " + unknownAnimal2.creatureType)
  println(" ++ +++++ ++ ")

  // overRIDING vs overLOADING

  // super

  // preventing overrides
  // 1 - use final on member
  // 2 - use final on the entire class
  // 3 - seal the class = extend classes in THIS FILE, prevent extension in other files

}
