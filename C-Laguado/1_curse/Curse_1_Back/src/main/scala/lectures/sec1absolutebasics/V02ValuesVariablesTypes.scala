package lectures.sec1absolutebasics

object V02ValuesVariablesTypes extends App {
  // Para ejecutar oprimir [Ctrl + Shift + F10]
  val int1: Int = 1
  val int2 = 2
  println("int1: " + int1 + " - int2: " + int2)

  // VALS are inmutable value
  // The COMPILER can infer types (no hay necesidad de especificar el tipo de dato)

  val str1: String = "hello"
  val str2 = "goodbye"
  println("str1: " + str1 + " - str2: " + str2)

  val bool1: Boolean = false
  val char1: Char = 'a'
  val int3: Int = int1
  val short1: Short = 4613
  println("bool1: " + bool1 + " - char1: " + char1 + " - int3: " + int3 + " - short1: " + short1)

  val long1: Long = 5273985273895237L
  val float1: Float = 1.02f
  val float2: Float = 2.03
  println("long1: " + long1 + " - float1: " + float1 + " - float2: " + float2)

  val double1 = 3.04
  val double2: Double = 3.1445
  val double3 = 5.06789
  println("double1: " + double1 + " - double2: " + double2 + " - double3: " + double3)

  // variables
  var var1: Int = 4
  println("[Before] var1: " + var1)
  var1 = 5 // side effects
  println("[Last] var1: " + var1)

}
