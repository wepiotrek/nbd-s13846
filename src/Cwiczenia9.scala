class Container[A] (private val content: A){
  def getContent: A = content
  def applyFunction[R](f: A => R): R = f(content)
}

trait Maybe[A] {
  def applyFunction[R, X](f: A => R): Maybe[R]
  //def getOrElse[E](e: E): Either[A, E]
}

class No extends Maybe[Nothing] {
  override def applyFunction[R, X](f: Nothing => R): Maybe[R] = this.asInstanceOf[Maybe[R]]
  //override def getOrElse[E](e: E): Either[Nothing, E] = Right(e)
  def getOrElse[E](e: E): E = e

}

class Yes[A] (val a: A) extends Maybe[A]  {
  override def applyFunction[R, X](f: A => R): Maybe[R] = new Yes[R](f(a))
  //override def getOrElse[E](e: E): Either[A, E] = Left(a)
  def getOrElse[E](e: E): A = a
}

object Cwiczenia9 {
  def main(args: Array[String]): Unit = {
    //zadanie 1
    val container = new Container("Piotrek")
    container.applyFunction(println)
    //zadanie 2
    val no = new No
    val yes = new Yes(5)
    println(no.isInstanceOf[Maybe[_]])
    println(yes.isInstanceOf[Maybe[_]])
    //zadanie 3
    println(no.applyFunction(println))
    yes.applyFunction(println)
    //zadanie 4
    println(no.getOrElse(2))
    println(yes.getOrElse("N/A"))
  }
}
