package lectures.sec2oop

import scala.language.postfixOps

/*
 * @author: Carlos.Laguado
 */
object V13MethodNotations2 extends App {
  // Para ejecutar oprimir [Ctrl + Shift + F10]
  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likesMovie(movie: String): Boolean = (movie == favoriteMovie)
    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie)
    def unary_! : String = s"$name, what things?!"
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    def apply(n: Int): String = s"$name watched $favoriteMovie $n times"
    def learns(thing: String) = s"$name is learning $thing"
    def learnsScala = this learns "Scala"
    def learnsScala2 = this learns ("Scala2")
  }

  val lucy = new Person("Lucy", "Inception")
  println("lucy.likesMovie('Inception'): " + lucy.likesMovie("Inception"))
  println("lucy likesMovie 'Inception' : " + (lucy likesMovie "Inception") + " - Es equivalente") // equivalent
  // infix notation = operator notation (syntactic sugar)
  /*
    1.  Overload the + operator
        mary + "the rockstar" => new person "Mary (the rockstar)"
    2.  Add an age to the Person class
        Add a unary + operator => new person with the age + 1
        +mary => mary with the age incrementer
    3.  Add a "learns" method in the Person class => "Mary learns Scala"
        Add a learnsScala method, calls learns method with "Scala".
        Use it in postfix notation.
    4.  Overload the apply method
        mary.apply(2) => "Mary watched Inception 2 times"
   */

  println("(lucy + 'the Rockstar').apply() : " + ( lucy + "the Rockstar").apply())
  println("( lucy).age                     : " + ( lucy).age)
  println("(+lucy).age                     : " + (+lucy).age)
  println("lucy learnsScala                : " + ( lucy learnsScala))
  println("lucy learnsScala2               : " + ( lucy learnsScala2))
  println("lucy(10)                        : " +   lucy(10))
  println("lucy.apply(10)                  : " +   lucy.apply(10))
  println("(!lucy)        : " + !lucy)
  println("(lucy.unary_!) : " + lucy.unary_! + " <> +(string)")

}
