package ComicsRss

import java.io.File
import java.net.{HttpURLConnection, URL}
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.{Instant, OffsetDateTime, ZoneOffset}
import scala.io.Source

/**
 *
 *
 * @author Ross M. Lodge
 */
case class MadamAndEvePageReader(comic: MadamAndEveComic, fileAccess: FileAccess) {

	val fileLocation = new File(System.getProperty("user.home"), "madam-and-eve.txt").getAbsolutePath

	def render(): List[ComicLink] = {
		val today = OffsetDateTime.now(ZoneOffset.UTC).truncatedTo(ChronoUnit.DAYS)
		val latest = findLatest()
		val fmt = DateTimeFormatter.ISO_OFFSET_DATE_TIME
		val oldOnes: collection.mutable.Map[String, (OffsetDateTime, Long)] = if (fileAccess.dataExists(fileLocation)) {
			collection.mutable.Map(
				Source.fromInputStream(fileAccess.readData(fileLocation))
					.getLines()
					.filter(_.length > 5)
					.map(
						line => {
							val segments = line.split(",")
							val x = (segments(0), (OffsetDateTime.from(fmt.parse(segments(1))), segments(2).toLong))
							x
						}
					)
					.toSeq: _*
			)
		} else {
			var last = 0
			latest
				.map(
					latestFileNumber => {
						collection.mutable.Map(
							((latestFileNumber - comic.backDate) until latestFileNumber)
								.map(
									fileNumber => {
										val current = fileNumber * 100 / latestFileNumber
										if (current > last) {
											last = current
											println(s"Getting madam and eve files $last%")
										}
										val hd = headerData(fileNumber)
										hd.map { case (date, url, size) => (today.minusDays(latestFileNumber - fileNumber), url, size) }
									}
								)
								.collect { case Some((date, url, size)) => (url, (date, size)) }
								.toSeq: _*
						)
					}
				)
				.getOrElse(collection.mutable.Map.empty)
		}
		val strips = latest
			.map(
				latestFileNumber => {
					(0 until 30)
						.map(x => (latestFileNumber - x, today.minusDays(x)))
						.filter(d => comic.schedule.accept(d._2.toLocalDate))
						.map(
							d => {
								val expectedUrl = fileNumberToUrl(d._1)
								oldOnes.get(expectedUrl)
									.map { case (foundDate, size) =>
										(expectedUrl, foundDate, size)
									}
									.orElse {
										val hd = headerData(d._1)
											.map { case (_, url, size) => (url, d._2, size) }
										hd.foreach { case (stripUrl, stripDate, stripSize) => oldOnes.put(stripUrl, (stripDate, stripSize)) }
										hd
									}
							}
						)
						.collect { case Some((stripUrl, stripDate, stripSize)) =>
							ComicLink(
								stripDate.toLocalDate,
								stripUrl,
								comic.name,
								Some(stripUrl, stripSize, "image/jpeg")
							)
						}
						.toList
				}
			)
			.getOrElse(List.empty)
		if (oldOnes.nonEmpty) {
			import java.io._
			val sw: StringWriter = new StringWriter()
			val pw = new PrintWriter(sw)
			try {
				oldOnes.toList
					.sortBy(_._1)
					.foreach { case (url, (date, size)) => pw.println(s"$url,${fmt.format(date)},$size") }
			} finally {
				fileAccess.writeData(fileLocation, sw.toString.getBytes("UTF-8"))
				pw.close()
			}
		}
		strips
	}

	def findLatest(): Option[Int] = {
		val html = Source.fromURL(comic.link)
		val firstLine: Option[String] = html.getLines().find(_.matches(""".*me([\d]+)\.jpg.*"""))
		firstLine
			.map(_.replaceAll(""".*me([\d]+)\.jpg.*""", "$1"))
			.map(_.toInt)
			.filter(
				number => {
					headerData(number).isDefined
				}
			)
	}

	def headerData(URLName: String): Option[(OffsetDateTime, String, Long)] = {
		try {
			HttpURLConnection.setFollowRedirects(false)
			val con: HttpURLConnection = new URL(URLName).openConnection.asInstanceOf[HttpURLConnection]
			con.setRequestMethod("HEAD")
			Option(OffsetDateTime.ofInstant(Instant.ofEpochMilli(con.getLastModified), ZoneOffset.UTC), URLName, con.getContentLengthLong)
		}
		catch {
			case e: Exception => None
		}
	}

	def headerData(fileNumber: Int): Option[(OffsetDateTime, String, Long)] = headerData(fileNumberToUrl(fileNumber))

	def fileNumberToUrl(fileNumber: Int): String = {
		f"${comic.link}/cartoons/me$fileNumber%06d.jpg"
	}

}
