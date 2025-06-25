package com.electronicmuse.comicsrss.providers

import com.electronicmuse.comicsrss.ComicLink
import com.electronicmuse.comicsrss.ComicLinkProvider
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.function.Supplier

data class MadamAndEveComicLinkProvider(
    val archivePath: String,
) : ComicLinkProvider {

    val regex = ".*(\\d{4}-\\d{2}-\\d{2})-(.+)".toRegex()

    override fun provideLinks(nowSupplier: Supplier<OffsetDateTime>): List<ComicLink> {
        val doc: Document = if (archivePath.lowercase().startsWith("http")) {
            Jsoup.connect(archivePath).get()
        } else {
            Jsoup.parse(File(archivePath))
        }

        val anchors = doc.select("div[class='media-item Madam & Eve'] > a")
        return anchors
            .flatMap { element ->
                val link: String? = element.attr("href")
                if (!link.isNullOrBlank()) {
                    val matchResult = regex.find(link)
                    val (comicDate: OffsetDateTime, comicTitle: String) = if (matchResult != null) {
                        val ld = DateTimeFormatter.ISO_LOCAL_DATE.parse(matchResult.groupValues[1])
                        LocalDate.from(ld).atTime(0, 0).atZone(ZoneId.of("Africa/Johannesburg")).toOffsetDateTime() to matchResult.groupValues[2].formatTitle()
                    } else {
                        nowSupplier.get() to link.trimEnd('/').replace(".+/(.+)".toRegex(), "$1").formatTitle()
                    }
                    listOf(ComicLink(comicDate, "M&E - $comicTitle", link))
                } else {
                    emptyList()
                }
            }
    }

    private fun String.formatTitle(): String = this.replace("\\W".toRegex(), " ").trim().replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}
