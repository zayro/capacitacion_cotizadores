package lectures.sec1absolutebasics

/*
 * @author: Carlos.Laguado
 */
object V05TypeInference extends App {
  // Para ejecutar oprimir [Ctrl + Shift + F10]
  // El compilador puede descifrar (realizar varios pasos) el tipo cuando no especificamos los tipos explicitamente
  val str1 = "Hello, world!"
  println("str1: " + str1)
  // el compilador lo transforma a algo asi:
  val str2: String = "Hello, world!"
  println("str2: String -> " + str2)

  val int1 = 2
  println("int1: " + int1)
  val str3 = int1 + " items"
  println("str3 (int1 + ' items'): " + str3)

  def addNum1(x: Int): Int = x + 1
  println("addNum1(3): Int = " + addNum1(3))

  def addNumAcum(num: Int, acum: Int) = num + acum
  println("addNumAcum(3, 2) = " + addNumAcum(3, 2))

}
