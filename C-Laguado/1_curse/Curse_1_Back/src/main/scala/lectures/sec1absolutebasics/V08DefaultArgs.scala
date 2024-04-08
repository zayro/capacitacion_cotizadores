package lectures.sec1absolutebasics

/*
 * @author: Carlos.Laguado
 */
object V08DefaultArgs extends App {
  // Para ejecutar oprimir [Ctrl + Shift + F10]
  def trFact(n: Int, acum: Int = 1): Int =
    if (n <= 1) acum
    else trFact(n - 1, n * acum)

  val fact5A = trFact(5, 2) // 5 * 4 * 3 * 2
  // trFact(5,   2) -> trFact(4,  10)
  // trFact(4,  10) -> trFact(3,  40)
  // trFact(3,  40) -> trFact(2, 120)
  // trFact(2, 120) -> trFact(1, 240)
  // trFact(1, 240) -> 240
  println("fact5A: " + fact5A)

  val fact5B = trFact(5) // 5 * 4 * 3 * 2
  // trFact(5,   1) -> trFact(4,  5)
  // trFact(4,   5) -> trFact(3,  20)
  // trFact(3,  20) -> trFact(2,  60)
  // trFact(2,  60) -> trFact(1, 120)
  // trFact(1, 120) -> 120
  println("fact5B: " + fact5B)

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("saving picture - " +
    "format: " + format + " - width: " + width + " - height: " + height)
  savePicture()
  /*
    1. pass in every leading argument
    2. name the arguments
   */
  savePicture(width = 800)
  savePicture(height = 600, format = "bmp", width = 900)
  savePicture(format = "gif")
}
