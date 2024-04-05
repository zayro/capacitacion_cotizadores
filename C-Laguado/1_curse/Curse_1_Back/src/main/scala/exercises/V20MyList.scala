package exercises

abstract class V20MyList[+A] {
  // Para ejecutar oprimir [Ctrl + Shift + F10]
  /*
     head = first element of  the  list
     tail = remainder (resto) of the list
     isEmpty = is this list empty
     add(int) => new list with this element added
     toString => a string representation of the list
   */
  def head: A
  def tail: V20MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): V20MyList[B]
  def printElements: String
  // llamada polimorfica
  override def toString: String = "[" + printElements + "]"
  // higher-order functions
  def map[B](transformer: MyTransformerTrait[A, B]): V20MyList[B]
  def filter(predicate: MyPredicateTrait[A]): V20MyList[A]
  def flatMap[B](transformer: MyTransformerTrait[A, V20MyList[B]]): V20MyList[B]
  // concatenation
  def ++[B >: A](list: V20MyList[B]): V20MyList[B]
}

//Para una lista vacia {??? // exresion que no devuelve nada (no arroja error porque se implementara)}
object EmptyObject20 extends V20MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException // si llama head de lista vacia, se bloquea con la excepcion
  def tail: V20MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): V20MyList[B] = new ConsClass20(element, EmptyObject20)
  def printElements: String = ".."
  def map[B](transformer: MyTransformerTrait[Nothing, B] ): V20MyList[B] = EmptyObject20
  def filter(predicate: MyPredicateTrait[Nothing]): V20MyList[Nothing] = EmptyObject20
  def flatMap[B](transformer: MyTransformerTrait[Nothing, V20MyList[B]]): V20MyList[B] = EmptyObject20
  def ++[B >: Nothing](list: V20MyList[B]): V20MyList[B] = list //listaBlanco concatenada con cualquier cosa, dev esa cosa
}

//Para una lista NO vacia
class ConsClass20[+A](h: A, t: V20MyList[A]) extends V20MyList[A] {
  def head: A = h
  def tail: V20MyList[A] = t
  def isEmpty: Boolean = false // porque contiene al menos un elemento
  def add[B >: A](element: B): V20MyList[B] = new ConsClass20(element, this) // this (es ese objeto 'V20MyList' como cola)
  def printElements: String =
    if (t.isEmpty) "" + h + ""
    else "" + h + " " + t.printElements
  /*
  * [1,2,3].map(n * 2)
    = new Cons(2, [2,3].map(n * 2))
    = new Cons(2, new Cons(4, [3].map(n * 2)))
    = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
    = new Cons(2, new Cons(4, new Cons(6, Empty)))              -> Da como resultado [2 4 6]
  * */
  def map[B](transformer: MyTransformerTrait[A, B]): V20MyList[B] =
    new ConsClass20(transformer.transform(h), t.map(transformer)) //la cola result se vinculara con el mismo transformer

  /*
  * [1,2,3].filter(n % 2 == 0)              -> valida el 1, no es par va al siguiente
    = [2,3].filter(n % 2 == 0)              -> valida el 2, es par, crea un dato en la lista
    = new Cons(2, [3].filter(n % 2 == 0))   -> valida el 3, no es par va al siguiente
    = new Cons(2, Empty.filter(n % 2 == 0)) -> valida Empty, no es NADA, queda asi
    = new Cons(2, Empty))                   -> Da como resultado [2]
  * */
  def filter(predicate: MyPredicateTrait[A]): V20MyList[A] =
    if (predicate.test(h)) new ConsClass20(h, t.filter(predicate))
    else t.filter(predicate) // la cabecera no pasa el predicado, por lo que no se incluira el resultado

  /*
    [1,2].flatMap(n => [n, n+1])                    -> transforma el 1 y daria [1,2], luego ++ [2].flatMap
    = [1,2] ++ [2].flatMap(n => [n, n+1])           -> Esta el [1,2], concatena (transforma el 2 y daria [2,3], luego ++ [Empty].flatMap)
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])-> Esta el [1,2,3,4], concatena (transforma Empty y daria [Empty], luego ++ [Empty])
    = [1,2] ++ [2,3] ++ Empty                       -> Esta el [1,2,3,4]
    = [1,2,2,3]                                     -> Esta el [1,2,3,4]
   */
  def flatMap[B](transformer: MyTransformerTrait[A, V20MyList[B]]): V20MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)

  /*
    [4,5] ++ [1,2,3]
      = new Cons(4, [5] ++ [1,2,3])
      = new Cons(4, new Cons(5, Empty ++ [1,2,3]))
      = new Cons(4, new Cons(5, new Cons(1, new Cons(2, new Cons(3)))))
   */
  def ++[B >: A](list: V20MyList[B]): V20MyList[B] = new ConsClass20(h, t ++ list)
}

trait MyPredicateTrait[-T] {
  def test(elem: T): Boolean
}

trait MyTransformerTrait[-A, B] {
  def transform(elem: A): B
}

object ListTest20 extends App {

  val listOfIntegers1: V20MyList[Int] = new ConsClass20(1, new ConsClass20(2, new ConsClass20(3, EmptyObject20)))
  println("listOfIntegers1.head: " + listOfIntegers1.head)
  println("listOfIntegers1.tail: " + listOfIntegers1.tail)
  println("listOfIntegers1.tail.head: " + listOfIntegers1.tail.head)
  println("listOfIntegers1.isEmpty: " + listOfIntegers1.isEmpty)
  println("listOfIntegers1.add(4): " + listOfIntegers1.add(4))
  println("listOfIntegers1.add(4).head: " + listOfIntegers1.add(4).head)
  println("listOfIntegers1.add(4).tail: " + listOfIntegers1.add(4).tail)
  println("listOfIntegers1.toString: " + listOfIntegers1.toString)
  println(" .. ----- .. ")

  /*
  * [1,2,3].map(n * 2)
    = new Cons(2, [2,3].map(n * 2))
    = new Cons(2, new Cons(4, [3].map(n * 2)))
    = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
    = new Cons(2, new Cons(4, new Cons(6, Empty)))              -> Da como resultado [2 4 6]
  * */
  println("listOfIntegers1.map( [1,2,3] ) {MyTransformerTrait[Int, Int]} (elem * 2) : " +
    " " + listOfIntegers1.map(new MyTransformerTrait[Int, Int] {
    override def transform(elem: Int): Int = elem * 2  // [1,2,3].map(n * 2) = [2,4,6] {transformador n * 2}
  }).toString)
  val listOfIntegersMap = listOfIntegers1.map(new MyTransformerTrait[Int, Int] {
    override def transform(elem: Int): Int = elem * 10 // [1,2,3].map(n * 2) = [2,4,6] {transformador n * 2}
  })
  println("listOfIntegersMap.map( [1,2,3] ) {MyTransformerTrait[Int, Int]}: (elem * 10) : " + listOfIntegersMap.toString)
  println(" ++ +++++ ++ ")

  /*
  * [1,2,3].filter(n % 2 == 0)              -> valida el 1, no es par va al siguiente
    = [2,3].filter(n % 2 == 0)              -> valida el 2, es par, crea un dato en la lista
    = new Cons(2, [3].filter(n % 2 == 0))   -> valida el 3, no es par va al siguiente
    = new Cons(2, Empty.filter(n % 2 == 0)) -> valida Empty, no es NADA, queda asi
    = new Cons(2, Empty))                   -> Da como resultado [2]
  * */
  println("listOfIntegers1.filter( n % 2 ) {MyPredicateTrait[Int]} ( elem % 2 == 0 {pares} )   : " +
    "" + listOfIntegers1.filter(new MyPredicateTrait[Int] {
    override def test(elem: Int): Boolean = ( (elem % 2) == 0 )
  }).toString)
  /*
  * [1,2,3].filter(n % 2 != 0)                           -> valida el 1, es impar, crea un dato en la lista
    = new Cons(1, [2,3].filter(n % 2 != 0))              -> valida el 2, no es impar va al siguiente
    = new Cons(1, [3].filter(n % 2 != 0))                -> valida el 3, es impar, crea un dato en la lista
    = new Cons(1, new Cons(3, Empty.filter(n % 2 != 0))) -> valida Empty, no es NADA, queda asi
    = new Cons(1, new Cons(3, Empty))                    -> Da como resultado [1,3]
  * */
  val listOfIntegersFilter = listOfIntegers1.filter(new MyPredicateTrait[Int] {
    override def test(elem: Int): Boolean = ((elem % 2) != 0)
  })
  println ("listOfIntegersFilter.filter( n % 2 ) {MyPredicateTrait[Int]} (elem % 2 != 0 {impar}): " + listOfIntegersFilter.toString)
  println (" ++ +++++ ++ ")
  println(" // //////////////////////////////////////////////////////////////////////////// // ")

  val listOfIntegers2: V20MyList[Int] = new ConsClass20(4, new ConsClass20(5, EmptyObject20))
  println("listOfIntegers1 ++ listOfIntegers2 ([1,2,3] ++ [4,5]): " + (listOfIntegers1 ++ listOfIntegers2).toString)
  println(" ++ +++++ ++ ")
  val listOfIntegers3 = listOfIntegers2 ++ listOfIntegers1
  println("listOfIntegers3 ([4,5] ++ [1,2,3]): " + listOfIntegers3.toString)

  println(" // //////////////////////////////////////////////////////////////////////////// // ")

  println("listOfIntegers1.flatMap(e => [n, n+1]) {[1,2,3]}       : " +
    "" + listOfIntegers1.flatMap(elem => new ConsClass20(elem, new ConsClass20(elem + 1, EmptyObject20))).toString)
  val listOfIntegers2FlatMap = listOfIntegers2.flatMap(new MyTransformerTrait[Int, V20MyList[Int]] {
    override def transform(elem: Int): V20MyList[Int] = (new ConsClass20(elem, new ConsClass20(elem + 1, EmptyObject20)))
  })
  println("listOfIntegers2FlatMap.flatMap(e => [n, n+1]) {[4,5]}  : " + listOfIntegers2FlatMap.toString)

  println(" // //////////////////////////////////////////////////////////////////////////// // ")

  val cloneListOfIntegers1: V20MyList[Int] = new ConsClass20(1, new ConsClass20(2, new ConsClass20(3, EmptyObject20)))
  println("listOfIntegers1.toString:                   -> " + listOfIntegers1.toString)
  println("cloneListOfIntegers1.toString:              -> " + cloneListOfIntegers1.toString)
  println("( listOfIntegers1 == cloneListOfIntegers1 ) -> " + (listOfIntegers1 == cloneListOfIntegers1) + " " +
    " { para estas listas (class) serían diferentes } ")
  println(" ++ +++++ ++ ")

  // Si no se utiliza la palabra clave (case class), para que sean iguales, se necesitaria definir un metodo recursivo
  //  que hubiese sido acordado para una lista, porque necesitaria comparar todos los elementos recursivamente

  // Esta caracteristica se agrega son escribir una sola linea, tan solo con escribir la palabra clave (case class)
  //  como se puede evidenciar en la lista del archivo V20MyList.
}
