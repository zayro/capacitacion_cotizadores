package lectures.sec2oop

/*
 * @author: Carlos.Laguado
 */
object V18Generics extends App {
  // Para ejecutar oprimir [Ctrl + Shift + F10]
  class MyListClass[A] { // A es un parametro de tipo
    // use the type A           {??? // exresion que no devuelve nada (no arroja error porque se implementara)}
      // [+A]: Covariant      (:Animal  ->  new Cat)     - type substitution (broad: polymorphism)
      // [ A]: Invariant      (:Animal  ->  new Animal)
      // [-A]: Contravariant  (:Cat     ->  new Animal)
  }

  class MyMapClass[Key, Value] // multiples parametros de tipo

  val listOfIntegers = new MyListClass[Int]
  //listOfIntegers.add(1)
  //listOfIntegers.add(2)
  println("listOfIntegers.getClass: " + listOfIntegers.getClass)

  val listOfStrings = new MyListClass[String]
  //listOfStrings.add("Uno")
  //listOfStrings.add("Dos")
  println("listOfStrings.getClass: " + listOfStrings.getClass)
  println(" ++ +++++ ++ ")

  // generic methods
  object MyListObject {
    def empty[A]: MyListClass[A] = ??? // exresion que no devuelve nada (no arroja error porque se implementara)}
  }
  val emptyListOfIntegers = MyListObject // MyListObject.empty[Int] -> este genera error por el ???
  println("emptyListOfIntegers.getClass: " + emptyListOfIntegers.getClass)
  //val emptyListOfIntegers = MyListObject.empty[Int]
  //println("emptyListOfIntegers: " + emptyListOfIntegers)
  println(" ++ +++++ ++ ")


  // variance problem
  // (don't stress about it)
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. yes, List[Cat] extends List[Animal] = COVARIANCE [+A]
  class CovariantListClass[+A]
  // type substitution (broad: polymorphism)
  val animal: Animal = new Cat
  val animalList: CovariantListClass[Animal] = new CovariantListClass[Cat] // cat es un animal
  println("animal.getClass          (: Animal)                      -> new Cat                    : " + animal.getClass)
  println("animalList.getClass [+A] (: CovariantListCla[Animal] -> new CovariantListCla[Cat]) : " + animalList.getClass)
  println(" .. ----- .. ")
  //animalList.add(new Dog) // ???  HARD QUESTION => we return a list of Animals

  // 2. NO = INVARIANCE (lista de gatos y listas de animales estan separadas)
  class InvariantListClass[A] // mi lista esta en su propio mundo y no puede sustituirse por otra
  val invariantAnimalList: InvariantListClass[Animal] = new InvariantListClass[Animal] // animal es solo animal
  println("invariantAnimalList.getClas  [A] (: InvariantLis[Animal] -> new [Animal]): " + invariantAnimalList.getClass)
  val invariantAnimalList2: InvariantListClass[Dog] = new InvariantListClass[Dog] // animal es solo animal
  println("invariantAnimalList2.getClas [A] (: InvariantLis[Dog]    -> new [Dog])   : " + invariantAnimalList2.getClass)
  println(" .. ----- .. ")

  // 3. Hell, no! CONTRAVARIANCE
  class ContravariantClass[-A] // Relacion opuesta, no tien sentido, porque Animal puede ser Dog y no solo Cat
  val contravariant: ContravariantClass[Cat] = new ContravariantClass[Animal] //reemplazo listCat por listAnimal
  println("contravariant.getClass [-A] (: ContravariantClass[Cat] -> new [Animal])        : " + contravariant.getClass)

  class TrainerContravariantClass[-A] // Un entrenador puede entrenar cualquier Animal, ya sea Dog, Cat
  val trainer: TrainerContravariantClass[Cat] = new TrainerContravariantClass[Animal] //Como entrena Animal tamb los Cat
  println("trainer.getClass       [-A] (: TrainerContravariantClass[Cat] -> new [Animal]) : " + trainer.getClass)
  println(" ++ +++++ ++ ")

  // bounded types (tipos acotados) - No resuelve problemas de varianza cuando se quiere escribir coleccions covariantes
  class CageClass[A <: Animal](animal: A) //Cage(Jaula) solo acepta parametros q son subtipos de Animal. recibe (Animal)
    //(Resticcion '<:') -> permite usar clases generik solo para ciertos tipos que son una subclase de un tipo diferente
  val cage = new CageClass(new Dog) // Permite usar Dog que es subclase de Animal
  println("cage.getClass  [A <: Animal] ( new CageClass(new Dog) )   : " + cage.getClass + " " +
    "{Permite usar Dog que es subclase de Animal}")
  val cage2 = new CageClass(new Cat) // Permite usar Cat que es subclase de Animal
  println("cage2.getClass [A <: Animal] ( new CageClass(new Cat) )   : " + cage2.getClass)
  val cage3 = new CageClass(new Animal) // Permite usar Cat que es subclase de Animal
  println("cage3.getClass [A <: Animal] ( new CageClass(new Animal) ): " + cage3.getClass)
  println(" ++ +++++ ++ ")

  class CageCatClass[C >: Cat](cat: C)
    //(Resticcion '>:') -> permite usar clases generik solo para supertipos de Cat, como Animal
  val cageCat = new CageCatClass(new Animal) // Permite usar Animal que es supertipo de Cat
  println("cageCat.getClass   [C >: Cat] ( new CageCatClass(new Animal) ) : " + cageCat.getClass + " " +
    "Permite usar Animal que es supertipo de Cat")
  val cageCat2 = new CageCatClass(new Cat)
  println("cageCat2.getClass  [C >: Cat] ( new CageCatClass(new Cat) )    : " + cageCat2.getClass)
  val cageCat3 = new CageCatClass(new Dog)
  println("cageCat3.getClass  [C >: Cat] ( new CageCatClass(new Dog) )    : " + cageCat3.getClass)
  println(" ++ +++++ ++ ")

  class Car
  // generic type needs proper bounded type (El tipo genérico necesita un tipo delimitado adecuado)
  //val newCage = new CageClass(new Car)  // No es posible, ya que esta acotada para aceptar Animal y sus subclases

  // expand MyList to be generic

  println(" // //////////////////////////////////////////////////////////////////////////// // ")

  class MyListCovariantClass[+A] { // A es un parametro de tipo [Covariant]
    // use the type A           {??? // exresion que no devuelve nada (no arroja error porque se implementara)}
    //(Resticcion '>:') -> permite usar clases generik solo para supertipos de A, como B
    def add[B >: A](element: B): MyListCovariantClass[B] = ???
    // Si una lista de A puse una de B que es supertipo de A, entonces se convertira en una lista de B que no esta en A
    //def add(element: A): MyListCovariantClass[A] = ??? // -> no funciona (Covariant type A occurs in contravariant position in type A of value element)
    // A = Cat
    // B = Animal (super tipo) - (Si agrego Dog q es Animal, entonces se convertira en lista B de Animal)
    // [+A]: Covariant      (:Animal  ->  new Cat)     - type substitution (broad: polymorphism)
    // [ A]: Invariant      (:Animal  ->  new Animal)
    // [-A]: Contravariant  (:Cat     ->  new Animal)
  }

  //class MyMapClass[Key, Value] // multiples parametros de tipo

  val listOfIntegersCovariant = new MyListCovariantClass[Int]
  println("listOfIntegersCovariant.getClass: " + listOfIntegersCovariant.getClass)

  val listOfStringsCovariant = new MyListCovariantClass[String]
  println("listOfStringsCovariant.getClass: " + listOfStringsCovariant.getClass)
  println(" ++ +++++ ++ ")

  val animalListCovariant: MyListCovariantClass[Animal] = new MyListCovariantClass[Cat] // cat es un animal
  println("animalListCovariant.getClass [+A] (: MyListCovariantClass[Animal] -> new MyListCovariantClass[Cat]) : " +
    " " + animalListCovariant.getClass)
  // val animalListCovariant2: MyListCovariantClass[Cat] = new MyListCovariantClass[Animal] // FALLA
  // val animalListCovariant3: MyListCovariantClass[Dog] = new MyListCovariantClass[Animal] // FALLA
  println(" .. ----- .. ")
  //animalListCovariant.add(new Animal)

  println(" ++ +++++ ++ ")
}
