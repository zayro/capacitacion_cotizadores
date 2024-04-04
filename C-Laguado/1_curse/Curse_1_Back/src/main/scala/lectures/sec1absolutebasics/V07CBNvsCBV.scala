package lectures.sec1absolutebasics

object V07CBNvsCBV extends App {
  // Para ejecutar oprimir [Ctrl + Shift + F10]
  //Se calcula antes, de que la funcion evalue el valor
  def calledByValue(x: Long): Unit = {
    println(" by value: " + x)
    println(" by value: " + x)
  }
  def calledTime(): Unit = {
    println(" t: " + System.nanoTime())
    println(" t: " + System.nanoTime())
  }
  //la expresion se pasa literal como este. La expresion se evalua todoel tiempo
  def calledByName(x: => Long): Unit = { // => 'Ejecuta el metodo dentro del bloque, por eso valores diferentes'
    println(" by name:  " + x)
    println(" by name:  " + x)
    println(" by name:  " + x)
  }

  println("calledByValue(System.nanoTime())") // 1257387745764245L
  calledByValue(System.nanoTime())
  println(" ++ +++++++++++++++++ ++ ")
  println("calledTime() - Similar a calledBtName")
  calledTime()
  println(" ++ +++++++++++++++++ ++ ")
  println("calledByName(System.nanoTime())")
  calledByName(System.nanoTime())
  println(" ++ +++++++++++++++++ ++ ")


  def infinite(): Int = 1 + infinite()
  //println("infinite(): Int = (1 + infinite())" + infinite() + " - [StackOverflowError]") // StackOverflowError

  def printFirst(x: Int, y: => Int) = println(x) // Infiere el tipo Unit
  //printFirst(infinite(), 34) // stack overflow
  println("printFirst(x=34, infinite()) -> println(x) [ " + printFirst(34, infinite()) + " ] " +
    " - esta expresion printFirst(..) devuelve una unidad ()")

  printFirst(45, infinite()) // Imprime primero la opcion de la x, con y no hace nada a pesar de llamarse

}
