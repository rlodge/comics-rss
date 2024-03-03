package ComicsRss

import java.time.LocalDate


case class ComicLink(dateTime: LocalDate, link: String, name: String, enclosure: Option[(String, Long, String)] = None, description: Option[String] = None) {

}
