package ComicsRss

import java.time.LocalDate

case class GoComicsDotComComic(n: String, link: String, schedule: Schedule = Schedule.SevenDays, backDating: Option[BackDating] = None) extends Comic {
	def goBackTo: LocalDate = backDating.filter(_.isOperative).map(_.goBackTo).getOrElse(LocalDate.now().minusDays(10))

	override def name: String = s"$n (GC)"
}

case class BackDating(ifWithinSevenDaysOf: LocalDate, goBackTo: LocalDate) {

	def isOperative = {
		val now = LocalDate.now()
		now.isAfter(ifWithinSevenDaysOf.minusDays(7)) && now.isBefore(ifWithinSevenDaysOf.plusDays(7))
	}

}
