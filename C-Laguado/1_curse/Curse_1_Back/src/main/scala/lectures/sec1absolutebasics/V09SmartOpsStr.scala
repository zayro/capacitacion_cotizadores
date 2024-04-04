package lectures.sec1absolutebasics

object V09SmartOpsStr extends App {
  // Para ejecutar oprimir [Ctrl + Shift + F10]
  val str: String = "Hello, I am learning Scala"
  println("str: " + str)
  println("str.charAt(4): " + str.charAt(4))
  println("str.charAt(7): " + str.charAt(7))
  println("str.charAt(16): " + str.charAt(16))
  println("str.substring(7, 16): " + str.substring(7, 16))
  println("str.split(' ').toList: " + str.split(" ").toList)
  println("str.startsWith('Hello'): " + str.startsWith("Hello"))
  println("str.startsWith('llo'): " + str.startsWith("llo"))
  println("str.replace(' ', '-'): " + str.replace(" ", "-"))
  println("str.toLowerCase(): " + str.toLowerCase())
  println("str.length: " + str.length)

  val aNumberString = "2"
  println("aNumberString: " + aNumberString)
  val aNumberToInt = aNumberString.toInt
  println("aNumberToInt: " + aNumberToInt)

  val strConcatened = 'a' +: aNumberString :+ 'z'
  println("strConcatened  -> ('a' +: aNumberString :+ 'z'): " + strConcatened)
  val strConcatened2 = "abc" +: aNumberString :+ "def"
  println("strConcatened2 -> ('abc' +: aNumberString :+ 'def'): " + strConcatened2)
  println("strConcatened2.toList: " + strConcatened2.toList)

  println("str.reverse: " + str.reverse)
  println("str.take(10): " + str.take(10))
  println("str.charAt(0): " + str.charAt(0))
  println("str.charAt(10): " + str.charAt(10))
  println("str.substring(0, 10): " + str.substring(0, 10))
  println("str.substring(10): " + str.substring(10) + " {del 10 hasta el final} ")

  // Scala-specific: String interpolators.
  // S-interpolators
  val name = "Carlos"
  val age = 38
  println("name: " + name + " - age: " + age)
  val greeting = s"Hello, my name is $name and I am $age years old"
  println("[S-interpolators] greeting: " + greeting)
  val anotherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years old."
  println("[S-interpolators] anotherGreeting: " + anotherGreeting)

  // F-interpolators
  val speed = 1.2f
  println("speed (1.2f): " + speed)
  val myth = f"$name can eat $speed%2.3f burgers per minute"
  println("[F-interpolators] myth ($speed%2.3f): " + myth)
  val myth2 = f"$name%s can eat $speed%2.5f burgers per minute"
  println("[F-interpolators] myth2 ($name%s)($speed%2.5f): " + myth2)

  // raw-interpolator (imprime caracteres literalmente, como \n)
  println(raw"This is a \n newline")
  val escaped = "This is a \n newline"
  println("[raw-interpolators] (raw'$escaped') (This is a /n newline)': " + raw"$escaped")
}
