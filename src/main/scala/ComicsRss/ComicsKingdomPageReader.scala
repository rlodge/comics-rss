package ComicsRss

import java.time.LocalDate
import java.time.format.DateTimeFormatter


/**
 *
 *
 * @author Ross M. Lodge
 */
case class ComicsKingdomPageReader(comic: ComicsKingdomComic) {

	def render() = {

		val today = LocalDate.now()
		val fmt = DateTimeFormatter.ISO_DATE
		(0 until 10)
			.map(x => today.minusDays(x))
			.filter(d => comic.schedule.accept(d))
			.map(d => ComicLink(
				d,
				comic.link + fmt.format(d),
				comic.name,
				description = Some(
					s"<p><a href='${comic.link + fmt.format(d)}'>Primary Link</a></p><p><a href='${comic.link.replace("v7.comicskingdom.net/comics", "comicskingdom.com") + fmt.format(d)}'>Secondary Link</a></p>"
				)
			))
	}

}
