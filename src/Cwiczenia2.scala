import scala.Int.int2long
import scala.math.BigInt.int2bigInt

object Cwiczenia2 {
  //Zadanie 1
  def opisDnia(day: String): String = day.toUpperCase match  {
    case "PONIEDZIAŁEK" => "Praca"
    case "WTOREK" => "Praca"
    case "ŚRODA" => "Praca"
    case "CZWARTEK" => "Praca"
    case "PIĄTEK" => "Praca"
    case "SOBOTA" => "Weekend"
    case "NIEDZIELA" => "Weekend"
    case _ => "Nie ma takiego dnia"
  }
  println(opisDnia("Poniedziałek"))
  println(opisDnia("sobota"))
  println(opisDnia("amelinium"))
  //Zadanie 2
  class KontoBankowe(){
    private var _stanKonta:Double = 0

    //konstruktor niedomyślny
    def this(stanKonta: Double){
      this
      _stanKonta=stanKonta
    }

    //getter
    def stanKonta = _stanKonta

    //metody
    def wplata(ilosc :Double): Unit ={
      if(ilosc>0)
        _stanKonta+=ilosc
      else
        throw new ArithmeticException("Wpłata ujemnej kwoty! Może użyć wypłaty?")
    }
    def wyplata(ilosc :Double): Unit ={
      if(ilosc>0)
        _stanKonta-=ilosc
      else
        throw new ArithmeticException("Wypłata ujemnej kwoty! Może użyć wpłaty?")
    }

    override def toString = "Konto o adresie "+Integer.toHexString(hashCode())+s" posiada: $stanKonta BTC"
  }
  var kb1 = new KontoBankowe()
  var kb2 = new KontoBankowe(33d)
  println(kb1.stanKonta)
  println(kb2.toString)
  kb1.wplata(45)
  kb2.wyplata(1.0001)
  println(kb1.toString)
  println(kb2.toString)
  //kb1.stanKonta = 999 powoduje prawidłowo błąd jeszcze przed kompilacją, a kb1.wplata(-100) w czasie runtime
  //Zadanie 3
  class Osoba(var imie: String, var nazwisko: String)

  var osoba1 = new Osoba("Piotr", "Abacki")
  var osoba2 = new Osoba("Jan", "Babacki")
  var osoba3 = new Osoba("Anastasiia", "Cbacka")
  var osoba4 = new Osoba("John", "Abacki")
  var osoba5 = new Osoba("Jan", "Matejko")

  def greetPerson(osoba: Osoba): String = osoba.nazwisko match {
    case "Abacki" => osoba.imie match {
      case "Piotr" => "Elo Piotrek, coś Ty taki zmęczony?"
      case "John" => "Whats up bro?"
      case _ => "Wiesz co "+osoba.imie+"? Znam Johna i Piotra. To Twoja rodzina?"
    }
    case "Babacki" => osoba.imie match {
      case  "Jan" => "Ooo! Witam Pana Jana."
      case _ => "Dzień dobry "+osoba.imie+". Jesteś kimś od Jana?"
    }
    case "Cbacka" => osoba.imie match{
      case "Anastasiia" => "Cześć Anaastasiia ( ͡° ͜ʖ ͡°)"
      case _ => "Witam, nie znam Cię "+ osoba.imie + "ale może znasz Anastasiię? To taka fajna dziewczyna."
    }
    case _ => "Dzień dobry " + osoba.imie + " " + osoba.nazwisko + ". Miło poznać taką wspaniałą osobę jak Ty."
  }

  println(greetPerson(osoba1))
  println(greetPerson(osoba2))
  println(greetPerson(osoba3))
  println(greetPerson(osoba4))
  println(greetPerson(osoba5))
//Zadanie 4
  def func(n: Int): Int = n+n
  def potrojnie(n: Int, func: Int => Int) = {
    func(func(func(n)))
  }
  println(potrojnie(9, func)) //5+5=10 -> 10+10=20 -> 20+20=40 (równważność mnożenia przez 8)
  //Zadanie 5
  class Osoba5(val imie: String, val nazwisko: String){
    protected var _podatek = 0.2d
    def podatek = _podatek
  }

  trait Pracownik extends Osoba5 {
    private var _pensja = 0d
    def pensja = _pensja-(_pensja*podatek)
    def pensja_=(n: Double) ={
      if(n > 0)
        _pensja = n
      else throw new ArithmeticException("Niewolnidzstwo zakazane!")
    }
  }
  trait Student extends Osoba5 {
    override def podatek: Double = 0
  }
  trait Nauczyciel extends Pracownik {
    override def podatek: Double =  0.1
  }
  val student = new Osoba5("Piotr", "Wezgraj") with Student
  val nauczyciel = new Osoba5("Mitsubihi", "Hoto") with Nauczyciel
  val pracownik = new Osoba5("Elżbieta", "Mrowisko") with Pracownik
  pracownik.pensja=1000
  nauczyciel.pensja=5000
  println(pracownik.pensja)// 800 to 1000 minus 20% podatku
  println(nauczyciel.pensja)//4500 to 5000 minus 10% podatku
  def main(args: Array[String]): Unit = {
  }
}
