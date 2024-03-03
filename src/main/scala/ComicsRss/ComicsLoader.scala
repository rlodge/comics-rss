package ComicsRss

import java.io.StringWriter
import java.time.{OffsetDateTime, ZoneId}
import java.time.format.DateTimeFormatter
import java.util.Base64
import scala.xml.{Elem, XML}

/**
 *
 *
 * @author Ross M. Lodge
 */
object ComicsLoader {

	def loadComics(configAccess: FileAccess, outputAccess: FileAccess): Unit = {
		val config: ComicsConfig = CurrentComicsConfig.getConfig

		val links = config.comics.flatMap(
			c => {
				val links = c match {
					case comic: SeattlePIComic => SeattlePIPageReader(comic).render()
					case comic: GoComicsDotComComic => GoComicsDotComPageReader(comic).render()
					case comic: ComicsKingdomComic => ComicsKingdomPageReader(comic).render()
					case comic: MadamAndEveComic => MadamAndEvePageReader(comic, configAccess).render()
				}
				links
			}
		).sortWith(
			(l1, l2) => {
				if (l1.dateTime.isBefore(l2.dateTime))
					true
				else
					l1.name < l1.name
			}
		).reverse

		val zone = ZoneId.of("America/Los_Angeles")

		//                                   Fri, 30 Mar 2012 00:00:00 -0700
		val fmt = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z")

		val fmt2 = DateTimeFormatter.ofPattern("EEEE, MMMM dd yyyy")

		val md = java.security.MessageDigest.getInstance("SHA-256")

		val enc = Base64.getEncoder

		val xml = <rss version="2.0">
			<channel>
				<title>Ross's Comics Subscriptions</title>
				<description>Links to various comics that don't have their own RSS feeds.</description>
				<link>http://www.electronicmuse.com</link>
				<lastBuildDate>
					{fmt.format(OffsetDateTime.now(zone))}
				</lastBuildDate>
				<pubDate>
					{fmt.format(OffsetDateTime.now(zone))}
				</pubDate>
				<ttl>120</ttl>{for (link <- links) yield {
				<item>
					<title>
						{String.format("%s for %s", link.name, fmt2.format(link.dateTime))}
					</title>
					<description>
						{link.description.getOrElse(String.format("<p><a href='%s'>%s</a></p>", link.link, link.name))}
					</description>
					<link>
						{link.link}
					</link>{if (link.enclosure.isDefined) {
						<enclosure url={link.enclosure.get._1} length={s"${link.enclosure.get._2}"} type={link.enclosure.get._3}/>
				}}<guid>
					{enc.encode(md.digest(link.link.getBytes))}
				</guid>
					<pubDate>
						{fmt.format(link.dateTime.atStartOfDay(ZoneId.of("America/Los_Angeles")).toOffsetDateTime)}
					</pubDate>
				</item>
			}}
			</channel>
		</rss>
		val w = new StringWriter()
		XML.write(w, xml, "utf-8", xmlDecl = true, doctype = null)
		outputAccess.writeData(config.outputFileName, w.toString.getBytes("UTF-8"))
	}

}
