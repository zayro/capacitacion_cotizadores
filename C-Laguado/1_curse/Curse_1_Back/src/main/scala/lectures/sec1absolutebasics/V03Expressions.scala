package lectures.sec1absolutebasics

object V03Expressions extends App {
  // Para ejecutar oprimir [Ctrl + Shift + F10]
  val exp1 = 1 + 2 // EXPRESSION
  println("exp1 (1 + 2): " + exp1)
  println("exp2 (2 + 3 * 4): " + (2 + 3 * 4) )
  // + - * / & | ^ << >> >>> (right shift with zero extension)

  val exp3 = (1 == exp1)
  println("exp3 (1 == exp1): " + exp3)
  println("exp4  (3 == exp1): " + (3 == exp1))
  // == != > >= < <=

  println("exp5 !(3 == exp1): " + !(3 == exp1))
  // ! && ||


  var var1 = 2
  println("[Before] var1: " + var1)
  var1 += 3 // also works with -= *= /= ..... side effects
  println("[Last] var1 (var1 += 3) : " + var1)

  // Instructions {le digo al pc que haga} (DO) vs Expressions {es algo que tiene un valor o tipo} (VALUE)

  // IF expression
  val bool1 = true
  println("bool1: " + bool1)
  val cond1 = if (bool1) 5 else 3 // the IF en scala es una EXPRESSION
  println("exp6 cond1   (if (bool1) 5 else 3): " + cond1)
  println("exp7 cond2:(if (bool1) 15 else 23): " + (if (bool1) 15 else 23))
  println("exp8 (1 + 3): " + (1 + 3))

  var i = 0
  val while1 = while (i < 10) { // esta expresion while devuelve una unidad
    println("{while1} i: " + i)
    i += 1
  }
  println("while1: [ " + while1 + " ]" + " - esta expresion while devuelve una unidad ()")

    // NEVER WRITE THIS AGAIN.
    // EVERYTHING in Scala is an Expression!. definir vocal, clase o paquete no serian expresiones

    println("var1: " + var1)
    val var2 = (var1 = 3) // Unit === void {unidad equivalente a vacio, no devuelve nada significativo}
    println("var2 (var1 = 3): [ " + var2 + " ] - esta expresion = devuelve una unidad ()")
    // side effects: println(), whiles, reassigning

    // Code blocks - rodeando entre llaves - sería un expresion como un todos 
    val aCodeBlock1 = {
      val y = 2
      val z = y + 1
      val str1 = "(y: " + y + "). "
      val str2 = "(z: " + z + "). "
      if (z > 2) str1 + str2 + "hello" else str1 + str2 + "goodbye"
    }
    println("aCodeBlock1 (z > 2): " + aCodeBlock1)

  val aCodeBlock2 = {
    val y = 0
    val z = y + 1
    val str1 = "[y: " + y + "]. "
    val str2 = "[z: " + z + "]. "
    val str3 = if (z > 2) "hello" else "goodbye"
    str1 + str2 + str3
  }
  println("aCodeBlock2 (z > 2): " + aCodeBlock2)
  
  // 1. difference between "hello world" vs println("hello world")?
  //      println() es una expresion que es un efecto secundario, y las expresiones devuelven una Unidad 
  // 2.
  val aCodeBlock3 = {
    2 < 3
  }
  println("aCodeBlock3 (2 < 3): " + aCodeBlock3)

  val aCodeBlock4 = {
    if (aCodeBlock3) 239 else 986
  }
  println("aCodeBlock4 [(if (aCodeBlock3) 239 else 986)]: " + aCodeBlock4)

  val aCodeBlock5 = {
    if (aCodeBlock3) 239 else 986
    42
  }
  println("aCodeBlock5 [42] (last line): " + aCodeBlock5)
}
