package lectures.sec2oop

object V14Objects extends App {
  // Para ejecutar oprimir [Ctrl + Shift + F10]
  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY (no conoce el concepto de "static")
  object PersonObject { // type + its only instance
    // puede tener ese tipo de funcionalidad estatica. "static"/"class" - level functionality
    val N_EYES = 2
    def canFly: Boolean = false
    // factory method
    def apply(mother: PersonClass, father: PersonClass): PersonClass = new PersonClass("Bobbie, son of " +
      "" + mother.name + " and " + father.name)
  }

  class PersonClass(val name: String) {
    // instance-level functionality
  }

  // COMPANIONS
  println("PersonObject.N_EYES: " + PersonObject.N_EYES)
  println("PersonObject.canFly: " + PersonObject.canFly)

  val mary = new PersonClass("Mary")
  val john = new PersonClass("John")
  println("(mary.name): " + (mary.name) + "")
  println("(john.name): " + (john.name) + "")
  println("(mary == john): " + (mary == john) + "")

  // Scala object = SINGLETON INSTANCE (objeto en su propio tipo, es unica instancia de ese tipo)
  val personObj1 = PersonObject // si dos cosas apuntan a este objeto, entonces son iguales
  val personObj2 = PersonObject
  println("personObj1.N_EYES: " + personObj1.N_EYES)
  println("personObj1.canFly: " + personObj1.canFly)
  println("personObj2.N_EYES: " + personObj2.N_EYES)
  println("personObj2.canFly: " + personObj2.canFly)
  println("(personObj1 == personObj2): " + (personObj1 == personObj2) + "")

  val bobbie = PersonObject(mary, john)
  println("(bobbie.name): " + bobbie.name)

  // Scala Applications = Scala object with
  //    def main(args: Array[String]): Unit
}
