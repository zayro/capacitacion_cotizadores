package lectures.sec1absolutebasics

/*
 * @author: Carlos.Laguado
 */
object V04Functions extends App {
  // Para ejecutar oprimir [Ctrl + Shift + F10]

  def functionA(a: String, b: Int): String = {
    a + " " + b
  }
  println("functionA(a, b): String : " + functionA("hello", 3))

  def functionB(a: String, b: Int) = {
    a + " - " + b
  }
  println("functionB(a, b)         : " + functionB("welcome", 5) + " [ Infiere tipo ]")

  def functionC(): Int = 42
  println("functionC(): Int : " + functionC())
  // println(functionC) // only works in Scala 2 - parameterless functions are invoked without parentheses

  def aRepeatedFunction(str: String, n: Int): String = {
    if (n == 1) str
    else str + " " + aRepeatedFunction(str, n - 1)
  }
  // aRepeatedFunction("hello", 3) -> "hello" + " " + aRepeatedFunction(aString, 2) -> hello hello hello
  // aRepeatedFunction("hello", 2) -> "hello" + " " + aRepeatedFunction(aString, 1) ->       hello hello
  // aRepeatedFunction("hello", 1) -> "hello"                                       ->             hello
  println("aRepeatedFunction(a, b): String : " + aRepeatedFunction("hello", 3))//->hello hello hello

  // WHEN YOU NEED LOOPS, USE RECURSION.
  def aFunctionWithSideEffects(aString: String): Unit = println(aString) // devuelve la unidad de impresion
  println("aFunctionWithSideEffects(a): Unit : [ " + aFunctionWithSideEffects("Carlos") + " ] - " +
    " esta funcion devuelve una unidad ()")
  aFunctionWithSideEffects("Laguado")

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = {
      println("[aSmallerFunction(a, b)] (a: " + a + ") - (b: " + b + ")")
      a + b // ultima linea del bloque, su valor sera retornado
    }
    aSmallerFunction(n, n - 1)
  }
  // aBigFunction(5) -> aSmallerFunction(5, 4) -> (5 + 4) = 9
  println("[aBigFunction(5): Int ] : " + aBigFunction(5))

  /*
    Exercises:
    1.  A greeting function (name, age) => "Hi, my name is $name and I am $age years old."
    2.  Factorial function 1 * 2 * 3 * .. * n
    3.  A Fibonacci function
        f(1) = 1
        f(2) = 1
        f(n) = f(n - 1) + f(n - 2)
    4.  Tests if a number is prime.
   */

  // 1.  A greeting function (name, age) => "Hi, my name is $name and I am $age years old."
  def greetingForKids(name: String, age: Int): String =
    " { Hi, my name is " + name + " and I am " + age + " years old. } "
  println("greetingForKids(name, age): String: " + greetingForKids("Charly", 12))

  // 2.  Factorial function 1 * 2 * 3 * .. * n
  def factorial(n: Int): Int =
    if (n <= 0) 1
    else n * factorial(n - 1)
  // factorial(5) -> 5 * factorial(4) -> 5 * 4 * 3 * 2 * 1 * 1 = 5 * 24 = 120
  // factorial(4) -> 4 * factorial(3) ->     4 * 3 * 2 * 1 * 1 = 4 *  6 =  24
  // factorial(3) -> 3 * factorial(2) ->         3 * 2 * 1 * 1 = 3 *  2 =   6
  // factorial(2) -> 2 * factorial(1) ->             2 * 1 * 1 = 2 *  1 =   2
  // factorial(1) -> 1 * factorial(0) ->                 1 * 1 = 1 *  1 =   1
  // factorial(0) -> 1                ->                     1 =      1 =   1
  println("factorial(5): Int : " + factorial(5))

  // 3.  A Fibonacci function
  def fibonacci(n: Int): Int =
    if (n <= 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)
  // fibonacci(8) -> fibonacci(7) + fibonacci(6) -> 13 + 8 -> 21
  // fibonacci(7) -> fibonacci(6) + fibonacci(5) ->  8 + 5 -> 13
  // fibonacci(6) -> fibonacci(5) + fibonacci(4) ->  5 + 3 ->  8
  // fibonacci(5) -> fibonacci(4) + fibonacci(3) ->  3 + 2 ->  5
  // fibonacci(4) -> fibonacci(3) + fibonacci(2) ->  2 + 1 ->  3
  // fibonacci(3) -> fibonacci(2) + fibonacci(1) ->  1 + 1 ->  2
  // fibonacci(2) ->                           1 ->  1
  // fibonacci(1) ->                           1 ->  1
  // 1 1 2 3 5 8 13 21
  println("fibonacci(8): Int : " + fibonacci(8))

  // 4.  Tests if a number is prime.
  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)
    }
    val num1 = n / 2
    println("num1: " + num1)
    isPrimeUntil(num1)
  }
  // isPrime(7) ->  num1: 3 | true && isPrimeUntil(2) -> true && {true} = true
  //      isPrimeUntil(2)  -> true && isPrimeUntil(1) -> true &&  true = {true}
  //      isPrimeUntil(1)  -> true
  // isPrime(12) -> num1: 6 | false && isPrimeUntil(5) -> false && {} = false
  // isPrime(13) -> num1: 6 | true && isPrimeUntil(5) -> true && {true} =  true
  //      isPrimeUntil(5)  -> true && isPrimeUntil(4) -> true && [true] = {true}
  //      isPrimeUntil(4)  -> true && isPrimeUntil(3) -> true && (true) = [true]
  //      isPrimeUntil(3)  -> true && isPrimeUntil(2) -> true && {true} = (true)
  //      isPrimeUntil(2)  -> true && isPrimeUntil(1) -> true &&  true  = {true}
  //      isPrimeUntil(1)  -> true
  println("isPrime(7): Boolean : " + isPrime(7))   // true
  println("isPrime(12): Boolean : " + isPrime(12)) // false
  println("isPrime(13): Boolean : " + isPrime(13)) // true
  println("isPrime(37): Boolean : " + isPrime(37))
  println("isPrime(2003): Boolean : " + isPrime(2003))
  val int1 = 37 * 17;
  println("int1: " + int1)
  println("isPrime(int1): Boolean : " + isPrime(int1))

}
