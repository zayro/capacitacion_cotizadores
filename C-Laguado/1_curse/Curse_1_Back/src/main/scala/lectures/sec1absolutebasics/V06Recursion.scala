package lectures.sec1absolutebasics

import scala.annotation.tailrec

object V06Recursion extends App {
  // Para ejecutar oprimir [Ctrl + Shift + F10]
  def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      println("Computing factorial of " + n + " - But first need factorial of (" + (n - 1) + ")")
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n + " - result: " + result)
      result
    }
  // factorial(5) -> 5 * factorial(4) -> 5 * 4 * 3 * 2 * 1 * 1 = 5 * 24 = 120
  // factorial(4) -> 4 * factorial(3) ->     4 * 3 * 2 * 1 * 1 = 4 *  6 =  24
  // factorial(3) -> 3 * factorial(2) ->         3 * 2 * 1 * 1 = 3 *  2 =   6
  // factorial(2) -> 2 * factorial(1) ->             2 * 1 * 1 = 2 *  1 =   2
  // factorial(1) -> 1
  println("factorial(5): Int : " + factorial(5))
  //println("factorial(5000): Int : " + factorial(5000)) // StackOverflowError! (desbordamiento de pila)
  
  def factorialWithHelper(n: Int): BigInt = {
    @tailrec // riesgo de cola. dira al compilador que esta func es recursiva de cola
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator) // TAIL RECURSION = use recursive call as the LAST expression
      //Recursividad de cola e inteligencia (guarda resultados intermedios, sin utilizar la memoria extra de pila)
    factHelper(n, 1)
  }
  // factorialWithHelper(5) -> factHelper(5, 1)        ->
  //       factHelper(5,   1) -> factHelper(4, 5 *  1) ->
  //       factHelper(4,   5) -> factHelper(3, 4 *  5) ->
  //       factHelper(3,  20) -> factHelper(2, 3 * 20) ->
  //       factHelper(2,  60) -> factHelper(1, 2 * 60) ->
  //       factHelper(1, 120) -> 120
  println("factorialWithHelper(5): BigInt : " + factorialWithHelper(5))
  println("factorialWithHelper(5000): BigInt : " + factorialWithHelper(5000) + " [ mas eficiente, no desborda la pila ]")

  // WHEN YOU NEED LOOPS, USE _TAIL_ RECURSION.
  /*Exercises:
    1.  Concatenate a string n times
    2.  Fibonacci function, tail recursive.
    3.  IsPrime function tail recursive
   */
  // 1.  Concatenate a string n times
  @tailrec // A method annotation which verifies that the method will be compiled with tail call optimization
  def concatenateTailrec(str: String, num: Int, accumulator: String): String =
    if (num <= 0) accumulator
    else concatenateTailrec(str, num - 1, str + " " + accumulator)
  // concatenateTailrec("hello", 3, "")                   -> concatenateTailrec("hello", 2, "hello ")
  // concatenateTailrec("hello", 2, "hello ")             -> concatenateTailrec("hello", 1, "hello hello ")
  // concatenateTailrec("hello", 1, "hello hello ")       -> concatenateTailrec("hello", 0, "hello hello hello ")
  // concatenateTailrec("hello", 0, "hello hello hello ") -> "hello hello hello "
  println("concatenateTailrec('hello', 3, ''): String : " + concatenateTailrec("hello", 3, ""))

  // 2.  Fibonacci function, tail recursive.
  def fibonacci(n: Int): Int = {
    @tailrec
    def fiboTailrec(num: Int, last: Int, nextToLast: Int): Int =
      if (num >= n) last
      else fiboTailrec(num + 1, last + nextToLast, last)

    if (n <= 2) 1
    else fiboTailrec(2, 1, 1)
  }
  // fibonacci(8) -> fiboTailrec(2, 1, 1) // esta seria la vase, los 3 primeros fibonnaci
  //    fiboTailrec(2,  1,  1) -> fiboTailrec(3,  2, 1)
  //    fiboTailrec(3,  2,  1) -> fiboTailrec(4,  3, 2)
  //    fiboTailrec(4,  3,  2) -> fiboTailrec(5,  5, 3)
  //    fiboTailrec(5,  5,  3) -> fiboTailrec(6,  8, 5)
  //    fiboTailrec(6,  8,  5) -> fiboTailrec(7, 13, 8)
  //    fiboTailrec(7, 13,  8) -> fiboTailrec(8, 21, 13)
  //    fiboTailrec(8, 21, 13) -> 21
  println("fibonacci(8): Int : " + fibonacci(8)) // 1, 1, 2, 3, 5, 8, 13, 21

  // 3.  IsPrime function tail recursive
  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailrec(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailrec(t - 1, n % t != 0 && isStillPrime)

    val num1 = n / 2
    println("num1: " + num1)
    isPrimeTailrec(num1, true)
  }
  // isPrime(7)                  -> num1: 3 | isPrimeTailrec(3, true)
  //    isPrimeTailrec(3, true)  -> isPrimeTailrec(2, 7 % 3 != 0 && true) -> 2, true
  //    isPrimeTailrec(2, true)  -> isPrimeTailrec(1, 7 % 2 != 0 && true) -> 1, true
  //    isPrimeTailrec(1, true)  -> true
  // isPrime(12)                 -> num1: 6 | isPrimeTailrec(6, true)
  //    isPrimeTailrec(6, true)  -> isPrimeTailrec(5, 12 % 6 != 0 && true) -> 5, false
  //    isPrimeTailrec(5, false) -> false
  println("isPrime(7): Boolean : " + isPrime(7))      // true
  println("isPrime(12): Boolean : " + isPrime(12))    // false
  println("isPrime(2003): Boolean : " + isPrime(2003))// true
  println("isPrime(629): Boolean : " + isPrime(629))  // false

  def tableWithHelper(num: Int, cont: Int): String = {
    @tailrec // riesgo de cola. dira al compilador que esta func es recursiva de cola
    def tableHelper(num: Int, cont: Int, accumulator: String): String =
      if (cont <= 0) accumulator
      else { // TAIL RECURSION = use recursive call as the LAST expression
        val result = num * cont
        println(num + " * " + cont + " = " + result + " - " + accumulator)
        tableHelper(num, cont - 1, result + "")
      }
    //Recursividad de cola e inteligencia (guarda resultados intermedios, sin utilizar la memoria extra de pila)
    tableHelper(num, cont, "")
  }
  // tableWithHelper(4, 5) -> tableHelper(4, 5, '')        ->
  //       tableHelper(4, 5, '')    -> tableHelper(4, 4, 4 * 5) -> 20
  //       tableHelper(4, 4, 4 * 5) -> tableHelper(4, 3, 4 * 4) -> 16
  //       tableHelper(4, 3, 4 * 4) -> tableHelper(4, 2, 4 * 3) -> 12
  //       tableHelper(4, 2, 4 * 3) -> tableHelper(4, 1, 4 * 2) ->  8
  //       tableHelper(4, 1, 4 * 2) -> tableHelper(4, 0, 4 * 1) ->  4
  //       tableHelper(4, 0, 4 * 1) ->                          ->  4
  println(" ++ ++++++++++ ++ ")
  println("tableWithHelper(5): String : " + tableWithHelper(5, 12))
  println(" ++ ++++++++++ ++ ")

  def tableWithHelper2(num: Int, limit: Int): String = {
    @tailrec // riesgo de cola. dira al compilador que esta func es recursiva de cola
    def tableHelper2(num: Int, initial: Int, limit: Int, accumulator: String): String =
      if (initial > limit) accumulator
      else { // TAIL RECURSION = use recursive call as the LAST expression
        val result = num * initial
        println(num + " * " + initial + " = " + result + " - " + accumulator)
        tableHelper2(num, initial + 1, limit, result + "")
      }
    //Recursividad de cola e inteligencia (guarda resultados intermedios, sin utilizar la memoria extra de pila)
    tableHelper2(num, 1, limit, "")
  }
  println("tableWithHelper2(5, 10): String : " + tableWithHelper2(5, 10))

}
