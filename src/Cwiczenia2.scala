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


  def main(args: Array[String]): Unit = {
  }
}
