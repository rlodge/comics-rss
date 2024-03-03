package ComicsRss

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 *
 *
 * @author Ross M. Lodge
 */
case class GoComicsDotComPageReader(comic: GoComicsDotComComic) {

	def render() = {

		val fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd")
		val now = LocalDate.now()
		Iterator.iterate(comic.goBackTo) { last => last.plusDays(1) }
			.takeWhile(x => x.isBefore(now) || x.isEqual(now))
			.filter(d => comic.schedule.accept(d))
			.map(d => ComicLink(d, s"${comic.link}/${fmt.format(d)}", comic.name))
			.toList
	}

}
