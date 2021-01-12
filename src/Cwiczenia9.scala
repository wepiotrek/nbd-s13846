class Container[A] (private val content: A){
  def getContent: A = content
  def applyFunction[R](f: A => R): R = f(content)
}

trait Maybe[A] {
  def applyFunction[R, X](f: A => R): Maybe[R]
  def getOrElse[B >: A](default: => B): B
}

class No extends Maybe[Nothing] {
  override def applyFunction[R, X](f: Nothing => R): Maybe[R] = this.asInstanceOf[Maybe[R]]
  override def getOrElse[B >: Nothing](default: => B): B = default
}

class Yes[A] (val a: A) extends Maybe[A]  {
  override def applyFunction[R, X](f: A => R): Maybe[R] = new Yes[R](f(a))
  override def getOrElse[B >: A](default: => B): B = a
}

object Cwiczenia9 {
  def main(args: Array[String]): Unit = {
    //zadanie 1
    val container = new Container("Piotrek")
    container.applyFunction(println) //Piotrek
    //zadanie 2
    val no = new No
    val yes = new Yes(5)
    println(no.isInstanceOf[Maybe[_]]) //true
    println(yes.isInstanceOf[Maybe[_]]) //true
    //zadanie 3
    println(no.applyFunction(println)) //No@2d6a9952
    yes.applyFunction(println) //5
    //zadanie 4
    println(no.getOrElse(2)) //2
    println(yes.getOrElse("N/A")) //5
  }
}
