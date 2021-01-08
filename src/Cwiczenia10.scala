object Cwiczenia10 {
  trait Maybe[A]{
    def applyFunction[R, X](f: A => R): Maybe[R]
    def getOrElse[E](e: E): Any
    def map[B](f: A => B):Maybe[B]
    def flatMap[B](f: A => B):Maybe[Any] //Yes -> Maybe[List[Any]], No ->Maybe[Any]
  }

  class No extends Maybe[Nothing] {
    override def applyFunction[R, X](f: Nothing => R): Maybe[R] = this.asInstanceOf[Maybe[R]]
    override def getOrElse[E](e: E): E = e
    override def map[B](f: Nothing => B): Maybe[B] = f(???).asInstanceOf[Maybe[B]]
    override def flatMap[B](f: Nothing => B): Maybe[Any] = new Yes(List(f(???).toString).flatten)
  }

  class Yes[A] (val a: A) extends Maybe[A]  {
    override def applyFunction[R, X](f: A => R): Maybe[R] = new Yes[R](f(a))
    override def getOrElse[E](e: E): A = a
    override def map[B](f: A => B): Maybe[B] = new Yes(f(a))
    override def flatMap[B](f: A => B): Maybe[Any] = new Yes(List(f(a).toString).flatten)

  }
  def main(args: Array[String]): Unit = {
    //Zadanie 1
    lazy val ax = Iterator.from(0)
    lazy val generator = for {
      a <- ax
      b <- 1 until a
      if a % b == 0
    } yield (a, b)
    for(i<- 1 to 20){
      println("Wywołanie "+i+" daje wynik "+generator.next())
    }
    val a = new Yes(5)
    //Zadanie 2
    a.applyFunction(println) //5
    a.map(f => f*10).applyFunction(println) //50

    val b = new Yes("Ala Ma KOTA")
    b.flatMap(_.toLowerCase).applyFunction(println) //List(a, l, a,  , m, a,  , k, o, t, a)
  }
}
