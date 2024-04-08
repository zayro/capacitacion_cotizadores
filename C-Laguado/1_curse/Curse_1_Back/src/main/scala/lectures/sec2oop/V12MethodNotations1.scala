package lectures.sec2oop

import scala.language.postfixOps

/*
 * @author: Carlos.Laguado
 */
object V12MethodNotations1 extends App {
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
  }

  val lucy = new Person("Lucy", "Inception")
  println("lucy.likesMovie('Inception'): " +  lucy.likesMovie("Inception"))
  println("lucy likesMovie 'Inception' : " + (lucy likesMovie "Inception") + " - Es equivalente") // equivalent
  // infix notation = operator notation (syntactic sugar)

  // "operators" in Scala
  val charly = new Person("Charly", "Fight Club")
  println("lucy.hangOutWith(charly): " + (lucy.hangOutWith(charly)) + " <> +(person)")
  println("lucy hangOutWith charly : " + (lucy hangOutWith charly) + " <> +(person)")
  println("lucy.+(charly)          : " + (lucy.+(charly)) + " <> +(person)")
  println("lucy + charly           : " + (lucy + charly) + " <> +(person)")

  println("1.+(2): " + (1.+(2)))
  println("1 + 2 : " + (1 + 2))

  val charlie = new Person("Charlie", "Happy Live")
  val charlie2 =  charlie.+("Fernandez")
  val charlie3 =  charlie + "Laguado"
  val charlie4 = (charlie + "Lopez").unary_+
  println("[charlie]].unary_!                                   : " + (charlie.unary_!) + " <> +(string)")
  println("[charlie2] [ charlie.+('Fernandez') ].unary_!        : " + (charlie2.unary_!) + " <> +(string)")
  println("[charlie3] [ charlie + 'Laguado'    ].unary_!        : " + (charlie3.unary_!) + " <> +(string)")
  println("[charlie4] [ charlie + 'Lopez'      ].unary_+.unary_!: " + (charlie4.unary_!) + " <> +(string).unary_+")

  // ALL OPERATORS ARE METHODS.
  // Akka actors have ! ?

  // prefix notation (operadores unarios)
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  println("x    (-1)    : " + (x))
  println("y (1.unary_-): " + (y) + " ... 'unary_' coloca el siguiente caracter operador como prefijo")
  // unary_ prefix only works with ->  - + ~ !

  println("(!lucy)        : " + !lucy)
  println("(lucy.unary_!) : " +  lucy.unary_! + " <> +(string)")

  val charlie5 = +(charlie + "Eusebio") // + <-> .unary_+
  println("[charlie5] +[ charlie + 'Eusebio' ]: " + (!charlie5) + " <> +(string).unary_+")

  // postfix notation (para metodos sin parametros)
  println(" (lucy.isAlive) : " +   lucy.isAlive)
  println(" (lucy isAlive) : " +  (lucy isAlive)) // only available with the scala.language.postfixOps import - discouraged
  println("!(lucy isAlive) : " + !(lucy isAlive))

  // apply
  println("[lucy.apply() ] : " + lucy.apply())
  println("[lucy()       ] : " + lucy()) // equivalent
  println("[lucy.apply(2)] : " + lucy.apply(2))
  println("[lucy(2)      ] : " + lucy(2)) // equivalent
}
