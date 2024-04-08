package lectures.sec2oop

/*
 * @author: Carlos.Laguado
 */
object V10PooBasic1 extends App {
  // Para ejecutar oprimir [Ctrl + Shift + F10]
  val person = new Person("Carlos", 26)
  println("person.x: " + person.x)
  println("person.age: " + person.age)
  person.greet("Laguado") // devuelve el Unit (println)
  person.greet()                   // devuelve el Unit (println)
  println(" -- ----- -- ")

  val person2 = new Person("Laguado")
  println("person2.age: " + person2.age)
  person2.greet("Charlie")
  person2.greet()
  println(" -- ----- -- ")

  val person3 = new Person()
  println("person3.age: " + person3.age)
  person3.greet("Charly")
  person3.greet()
  println(" ++ +++++++++ ++ ")
}

// constructor - // al colocar val al campo, se puede obtener luego, y la convierte en miembro de la clase
class Person(name: String, val age: Int = 0) { // espacios de memoria reales ajustados al codigo y estructura de datos
  // body
  val x = 2
  println("x: " + x)
  println("1 + 3: " + (1 + 3) + " - Se ejecuta el cuerpo de la clase")
  // method
  def greet(lastName: String): Unit = println(s"${this.name} says: Hi, $lastName, and i have ${age} old years")
  // overloading
  def greet(): Unit = println(s"Hi, I am $name and have $age old years")
  // multiple constructors secundarios
  def this(name: String) = this(name, 0)
  def this() = this("Charlie Fernandez")
}
