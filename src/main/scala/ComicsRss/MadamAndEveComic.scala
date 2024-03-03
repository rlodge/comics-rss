package ComicsRss

case class MadamAndEveComic(backDate: Int = 30) extends Comic {

	override def name: String = "Madam and Eve"

	override def schedule: Schedule = Schedule.SevenDays

	override def link: String = "http://www.madamandeve.co.za"

}
