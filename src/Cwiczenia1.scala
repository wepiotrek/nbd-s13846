object Cwiczenia1 {
  val dniTygodnia = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")

  def dniFor(dni: List[String]): String = {
    var out = ""
    for (d <- dniTygodnia) out += d + ", "
    out
  }
  def main(args: Array[String]): Unit = {
    println("Hello, world")
    println(dniFor(dniTygodnia))
  }

}
