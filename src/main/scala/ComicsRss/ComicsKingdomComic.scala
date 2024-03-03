package ComicsRss

case class ComicsKingdomComic(n: String, link: String, schedule: Schedule = Schedule.SevenDays) extends Comic {
	override def name: String = s"$n (CK)"
}
