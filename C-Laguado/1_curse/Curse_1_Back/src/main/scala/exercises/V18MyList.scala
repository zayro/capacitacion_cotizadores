package exercises

abstract class V18MyList[+A] {
  // Para ejecutar oprimir [Ctrl + Shift + F10]
  /*
     head = first element of  the  list
     tail = remainder (resto) of the list
     isEmpty = is this list empty
     add(int) => new list with this element added
     toString => a string representation of the list
   */
  def head: A
  def tail: V18MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): V18MyList[B]
  def printElements: String
  // llamada polimorfica
  override def toString: String = "[" + printElements + "]"
}

//Para una lista vacia {??? // exresion que no devuelve nada (no arroja error porque se implementara)}
object EmptyObject18 extends V18MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException // si llama head de lista vacia, se bloquea con la excepcion
  def tail: V18MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): V18MyList[B] = new ConsClass18(element, EmptyObject18)
  def printElements: String = ".."
}

//Para una lista NO vacia
class ConsClass18[+A](h: A, t: V18MyList[A]) extends V18MyList[A] {
  def head: A = h
  def tail: V18MyList[A] = t
  def isEmpty: Boolean = false // porque contiene al menos un elemento
  def add[B >: A](element: B): V18MyList[B] = new ConsClass18(element, this) // this (es ese objeto 'V18MyList' como cola)
  def printElements: String =
    if (t.isEmpty) "" + h + ""
    else "" + h + " " + t.printElements
}

object ListTest18 extends App {
  val listOfIntegers1: V18MyList[Int] = new ConsClass18(1, new ConsClass18(2, new ConsClass18(3, EmptyObject18)))
  println("listOfIntegers1.head: " + listOfIntegers1.head)
  println("listOfIntegers1.tail: " + listOfIntegers1.tail)
  println("listOfIntegers1.tail.head: " + listOfIntegers1.tail.head)
  println("listOfIntegers1.isEmpty: " + listOfIntegers1.isEmpty)
  println("listOfIntegers1.add(4): " + listOfIntegers1.add(4))
  println("listOfIntegers1.add(4).head: " + listOfIntegers1.add(4).head)
  println("listOfIntegers1.add(4).tail: " + listOfIntegers1.add(4).tail)
  println("listOfIntegers1.toString: " + listOfIntegers1.toString)
  println(" .. ----- .. ")

  val listOfIntegers2: V18MyList[Int] = new ConsClass18(4, new ConsClass18(5, EmptyObject18))
  println("listOfIntegers2.head: " + listOfIntegers2.head)
  println("listOfIntegers2.tail: " + listOfIntegers2.tail)
  println("listOfIntegers2.tail.head: " + listOfIntegers2.tail.head)
  println("listOfIntegers2.isEmpty: " + listOfIntegers2.isEmpty)
  println("listOfIntegers2.add(7): " + listOfIntegers2.add(7))
  println("listOfIntegers2.add(7).head: " + listOfIntegers2.add(7).head)
  println("listOfIntegers2.add(7).tail: " + listOfIntegers2.add(7).tail)
  println("listOfIntegers2.toString: " + listOfIntegers2.toString)
  println(" .. ----- .. ")

  val listOfIntegers3: V18MyList[Int] = EmptyObject18
  println("listOfIntegers3.isEmpty: " + listOfIntegers3.isEmpty)
  println("listOfIntegers3.add(8): " + listOfIntegers3.add(8))
  println("listOfIntegers3.toString: " + listOfIntegers3.toString)
  println(" ++ +++++ ++ ")

  val listOfString1: V18MyList[String] = new ConsClass18("Carlos", new ConsClass18("Laguado", EmptyObject18))
  println("listOfString1.head: " + listOfString1.head)
  println("listOfString1.tail: " + listOfString1.tail)
  println("listOfString1.tail.head: " + listOfString1.tail.head)
  println("listOfString1.isEmpty: " + listOfString1.isEmpty)
  println("listOfString1.add('Fernandez'): " + listOfString1.add("Fernandez"))
  println("listOfString1.add('Fernandez').head: " + listOfString1.add("Fernandez").head)
  println("listOfString1.add('Fernandez').tail: " + listOfString1.add("Fernandez").tail)
  println("listOfString1.toString: " + listOfString1.toString)
  println(" .. ----- .. ")

  val listOfString2: V18MyList[String] = EmptyObject18
  println("listOfString2.isEmpty: " + listOfString2.isEmpty)
  println("listOfString2.add('Carlos'): " + listOfString2.add("Carlos"))
  println("listOfString2.toString: " + listOfString2.toString)
  println(" ++ +++++ ++ ")

}
