package ComicsRss

import java.time.LocalDate
import java.time.format.DateTimeFormatter


/**
 *
 *
 * @author Ross M. Lodge
 */
case class SeattlePIPageReader(comic: SeattlePIComic) {

	def render() = {

		val today = LocalDate.now()
		val fmt = DateTimeFormatter.ISO_DATE
		(0 until 10)
			.map(x => today.minusDays(x))
			.filter(d => comic.schedule.accept(d))
			.map(d => ComicLink(d, comic.link + fmt.format(d) + "/?btype=web", comic.name))
	}

}
