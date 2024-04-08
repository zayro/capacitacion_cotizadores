package lectures.sec2oop

/*
 * @author: Carlos.Laguado
 */
object V19AnonymousClasses extends App {
  // Para ejecutar oprimir [Ctrl + Shift + F10]
  abstract class Animal {
    def eat: Unit
  }

  // anonymous class
  val funnyAnimalAnonymous1: Animal = new Animal {
    override def eat: Unit = println("jejejejejejeje")
  }

  //equivalent with
  class $$anon$$1 extends Animal {
    override def eat: Unit = println("hahahahahaha")
  }
  val funnyAnimal1Anonymous2: Animal = new $$anon$$1

  val funnyAnimalAnonymous3: Animal = new Animal {
    override def eat: Unit = println("sisisisisisisi")
  }

  funnyAnimalAnonymous1.eat
  println("funnyAnimalAnonymous1.getClass  : " + funnyAnimalAnonymous1.getClass)
  println(" ++ +++++ ++ ")

  funnyAnimal1Anonymous2.eat
  println("funnyAnimal1Anonymous2.getClass : " + funnyAnimal1Anonymous2.getClass)
  println(" ++ +++++ ++ ")

  funnyAnimalAnonymous3.eat
  println("funnyAnimalAnonymous3.getClass  : " + funnyAnimalAnonymous3.getClass)
  println(" ++ +++++ ++ ")

  println(" // //////////////////////////////////////////////////////////////////////////// // ")

  class Person(val name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  val charlyPerson = new Person("Charly") {
    override def sayHi: Unit = println(s"Hi, my name is Laguado, how can I be of service?")
  }
  charlyPerson.sayHi
  println("charlyPerson.getClass  : " + charlyPerson.getClass)
  println(" ++ +++++ ++ ")

  val fernandezPerson = new Person("Fernandez") {
    override def sayHi: Unit = println(s"Hi, my name is ${this.name}, how can I be of service?")
  }
  fernandezPerson.sayHi
  println("fernandezPerson.getClass  : " + fernandezPerson.getClass)
  println(" ++ +++++ ++ ")

  val lucyPerson: Person = new Person("Lucy")
  lucyPerson.sayHi
  println("lucyPerson.getClass  : " + lucyPerson.getClass)
  println(" ++ +++++ ++ ")

  println(" // //////////////////////////////////////////////////////////////////////////// // ")

  trait AnimalTwo {
    def eats: Unit = println("eat supertipo")
  }

  val catAnimal = new AnimalTwo {
    override def eats: Unit = println("miau miau miau")
  }
  catAnimal.eats
  println("[trait] catAnimal.getClass  : " + catAnimal.getClass)
  println(" ++ +++++ ++ ")

  /*
    Exercises:
    1.  Generic trait MyPredicate[-T] with a little method test(T) => Boolean {probar si una valor tipo T pasa una condicion}
          Cada subclase de MyPredicate[-T] tendra una implem diferente de ese peq metodo, si el T pasa una condicion
    2.  Generic trait MyTransformer[-A, B] with a method transform(A) => B {transforma A en B}
    3.  MyList:
        - map(transformer) => MyList  {toma el transformador y devuelve nueva lista }
        - filter(predicate) => MyList {filtro de tipo diferente que toma mi predicado}
        - flatMap(transformer from A to MyList[B]) => MyList[B] {toma un trasnformador de A a mi lista de B y regresa mi lista de B}

        class EvenPredicate extends MyPredicate[Int] {predicado de clase par que extiende predicado Int}
                          {predicado par tomara un int com param y devolvera si es par o no}
        class StringToIntTransformer extends MyTransformer[String, Int]

        [1,2,3].map(n * 2) = [2,4,6]                      {transformador n * 2}
        [1,2,3,4].filter(n % 2) = [2,4]                   {filtra numeros pares}
        [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]   {devolvera concatenacion de peq sublistas para cada elemento}
   */
}
