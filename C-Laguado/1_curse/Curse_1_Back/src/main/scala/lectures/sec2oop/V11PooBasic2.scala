package lectures.sec2oop

object V11PooBasic2 extends App {
  // Para ejecutar oprimir [Ctrl + Shift + F10]
  println(" ++ +++++++++ ++ ")
  val author = new Writer("Charles", "Laguado", 1812)
  val imposter = new Writer("Carlos", "Fernandez", 1812)
  val imposter2 = new Writer("Charles", "Laguado", 1812)
  println("author.fullName: " + author.fullName + " - age: " + author.year)
  println("imposter.fullName: " + imposter.fullName + " - age: " + author.year)
  println("imposter2.fullName: " + imposter2.fullName + " - age: " + author.year)

  val novel = new Novel("Great Expectations", 1861, author)
  println("novel.authorAge: " + novel.authorAge)
  println("novel.isWrittenBy(imposter): " + novel.isWrittenBy(imposter) + " {'Great Expectations', 1861, author }")
  println("novel.isWrittenBy(imposter2): " + novel.isWrittenBy(imposter2) + " {'Great Expectations', 1861, author }")
  println("novel.isWrittenBy(author): " + novel.isWrittenBy(author) + " {'Great Expectations', 1861, author }")

  val novel2 = novel.copy(1912)
  println("novel2.authorAge: " + novel2.authorAge + " [ .copy(1912) ] ")
  println("novel2.isWrittenBy(imposter): " + novel2.isWrittenBy(imposter) + " {'Great Expectations', 1912, author }")
  println("novel2.isWrittenBy(author): " + novel2.isWrittenBy(author) + " {'Great Expectations', 1912, author }")
  println(" ++ +++++++++ ++ ")

  val counter = new Counter
  counter.print
  println(" -- ----- -- ")
  counter.incBlock.print
  println(" -- ----- -- ")
  counter.incBlock.incBlock.print
  println(" -- ----- -- ")
  counter.increm(3).print
  println(" -- ----- -- ")
  counter.decBlock.print
  println(" -- ----- -- ")
  counter.increm(8).decBlock.decBlock.decrem(2).incBlock.print
  println(" ++ +++++++++ ++ ")
}

/*
Novel and a Writer

Writer: first name, surname, year
  - method fullname

Novel: name, year of release, author
- authorAge
- isWrittenBy(author)
- copy (new year of release) = new instance of Novel
*/
class Writer(firstName: String, surname: String, val year: Int) { // al colocar val al campo, se puede obtener luego
  def fullName: String = firstName + " " + surname
}

class Novel(name: String, year: Int, author: Writer) {
  def authorAge = year - author.year

  def isWrittenBy(author: Writer) = (author == this.author)

  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

/*
Counter class
  - receives an int value
  - method current count
  - method to increment/decrement => new Counter
  - overload inc/dec to receive an amount
*/
class Counter(val count: Int = 0) {
  def incBlock = {
    val num = count + 1
    println("Incrementing - count: " + num)
    new Counter(num) // immutability
  }

  def decBlock = {
    val num = count - 1
    println("Decrementing . count: " + num)
    new Counter(num)
  }

  def increm(n: Int): Counter = {
    if (n <= 0) this
    else incBlock.increm(n - 1)
  }

  def decrem(n: Int): Counter =
    if (n <= 0) this
    else decBlock.decrem(n - 1)

  def print = println(count)
}
