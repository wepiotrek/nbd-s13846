import scala.::
import scala.annotation.tailrec

object Cwiczenia1 {
  val dniTygodnia = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")

  //1a
  def dniFor(dni: List[String]): String = {
    var out = ""
    for (d <- dni) out += d + ", "
    out.dropRight(2)
  }
  //1b
  def dniForNaP(dni: List[String]): String = {
    var out = List[String]()
    for (d <- dni) if(d.toUpperCase().startsWith("P")) out = d::out
    dniFor(out)
  }
  //1c
  def dniWhile(dni: List[String]): String = {
    var out = ""
    var i: Int = 0
    while(i < dni.length){
      out += dni(i) + ", "
      i+=1
    }
    out.dropRight(2)
  }
  //2a
  def dniRek[T](dni: List[T]): String = dni match {
    case Nil => ""
    case _ => dni.head + ", " + dniRek(dni.tail)
  }
  //2b
  def dniRekWst[T](dni: List[T]): String = dni match {
    case Nil => ""
    case _ => dniRekWst(dni.tail) + ", " + dni.head
  }
  //3
  def dniTail[T](dni: List[T]): String = {
    @tailrec
    def appendStr[T](dni: List[T], str: String): String = dni match {
      case Nil => str.dropRight(2)
      case head :: tail => appendStr(tail, str + head + ", ")
    }
    appendStr(dni, "")
  }
  //4a
  def dniFoldl(dni: List[String]): String = {
    dni.foldLeft(""){ (acc, item) =>
      if(acc.isEmpty) acc + item
      else acc + ", " + item
    }
  }
  //4b
  def dniFoldr(dni: List[String]): String = {
    dni.foldRight(""){ (acc, item) =>
      if(item.isEmpty) acc + item
      else acc + ", " + item
    }

  }
  //4c
  def dniFoldlP(list: List[String]): String = {
    list.filter(_.toUpperCase()
      .startsWith("P")
    ) .foldLeft(""){ (acc, item) =>
      if(acc.isEmpty) acc + item
      else acc + ", " + item
    }
  }
  //5
  val produkty = Map (
    "CPU" -> 20d,
    "GPU" -> 30d,
    "Płyta główna" -> 19.9d,
    "Laptop gamingowy" -> 59.9d,
    "Gra komputerowa o czołgach" -> 199d
  )
  val produktyRabat = produkty.transform((_, v) => v * 0.9)
  //6
  val krotka = ("kb", 3d, None)
  def printKrotka[A, B, C](krotka: (A, B, C)): Unit = {
    println(krotka._1 + " - " + krotka._2 + " - " + krotka._3)
  }
  //7
  def getList[T](l: List[T], i: Int): Option[T] = {
    try {
      Some(l(i))
    } catch {
      case _: Exception => None
    }
  }
  //8
  def bezZer(list: List[Int]): List[Int] = {

    @tailrec
    def nowaLista(list: List[Int], listR: List[Int]): List[Int] = list match{
      case Nil => listR
      case head :: tail => {
        if(head == 0) nowaLista(tail, listR)
        else nowaLista(tail, listR.appended(head))
      }
    }
    nowaLista(list, List.empty[Int])
  }
  //9
  def plusJeden(list: List[Int]) = list.map(x => x + 1)
  //10
  def filtr(list: List[Double]): List[Double] = {
    list.filter(x => x >= -5)
      .filter(x => x <= 12)
      .map(x => x.abs)
  }
  def main(args: Array[String]): Unit = {
    println("Hello, world")
    println(dniForNaP(dniTygodnia))
    println(dniWhile(dniTygodnia))
  }

}
