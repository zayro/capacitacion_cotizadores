package exercises

abstract class V17MyList {
  // Para ejecutar oprimir [Ctrl + Shift + F10]
  /*
     head = first element of  the  list
     tail = remainder (resto) of the list
     isEmpty = is this list empty
     add(int) => new list with this element added
     toString => a string representation of the list
   */
  def head: Int
  def tail: V17MyList
  def isEmpty: Boolean
  def add(element: Int): V17MyList
  def printElements: String
  // llamada polimorfica
  override def toString: String = "[" + printElements + "]"
}

//Para una lista vacia {??? // exresion que no devuelve nada (no arroja error porque se implementara)}
object EmptyObject extends V17MyList {
  def head: Int = throw new NoSuchElementException // si llama head de lista vacia, se bloquea con la excepcion
  def tail: V17MyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(element: Int): V17MyList = new ConsClass(element, EmptyObject)
  def printElements: String = ".."
}

//Para una lista NO vacia
class ConsClass(h: Int, t: V17MyList) extends V17MyList {
  def head: Int = h
  def tail: V17MyList = t
  def isEmpty: Boolean = false // porque contiene al menos un elemento
  def add(element: Int): V17MyList = new ConsClass(element, this) // this (es ese objeto 'V17MyList' como cola)
  def printElements: String =
    if (t.isEmpty) h + ""
    else h + " " + t.printElements
}

object ListTest17 extends App {
  val listOfIntegers1: V17MyList = new ConsClass(1, new ConsClass(2, new ConsClass(3, EmptyObject)))
  println("listOfIntegers1.head: " + listOfIntegers1.head)
  println("listOfIntegers1.tail: " + listOfIntegers1.tail)
  println("listOfIntegers1.tail.head: " + listOfIntegers1.tail.head)
  println("listOfIntegers1.isEmpty: " + listOfIntegers1.isEmpty)
  println("listOfIntegers1.add(4): " + listOfIntegers1.add(4))
  println("listOfIntegers1.add(5).head: " + listOfIntegers1.add(5).head)
  println("listOfIntegers1.toString: " + listOfIntegers1.toString)
  println(" ++ +++++ ++ ")
  val listOfIntegers2: V17MyList = new ConsClass(4, new ConsClass(5, EmptyObject))
  println("listOfIntegers2.head: " + listOfIntegers2.head)
  println("listOfIntegers2.tail: " + listOfIntegers2.tail)
  println("listOfIntegers2.tail.head: " + listOfIntegers2.tail.head)
  println("listOfIntegers2.isEmpty: " + listOfIntegers2.isEmpty)
  println("listOfIntegers2.add(7): " + listOfIntegers2.add(7))
  println("listOfIntegers2.add(8).head: " + listOfIntegers2.add(8).head)
  println("listOfIntegers2.add(9).tail: " + listOfIntegers2.add(9).tail)
  println("listOfIntegers2.toString: " + listOfIntegers2.toString)
  println(" ++ +++++ ++ ")
  val listOfIntegers3: V17MyList = EmptyObject
  println("listOfIntegers3.isEmpty: " + listOfIntegers3.isEmpty)
  println("listOfIntegers3.add(8): " + listOfIntegers3.add(8))
  println("listOfIntegers3.toString: " + listOfIntegers3.toString)
  println(" ++ +++++ ++ ")
}
