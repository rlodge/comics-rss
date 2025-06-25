package com.electronicmuse.comicsrss.providers

import com.electronicmuse.comicsrss.ComicLink
import com.electronicmuse.comicsrss.ComicLinkProvider
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.function.Supplier

interface StaticComicLinkProvider : ComicLinkProvider {

    val titleFormat: DateTimeFormatter
    val rootUrl: String
    val numberOfDays: Int
    val paths: List<ComicPathAndSchedule>
    val pathFormat: DateTimeFormatter
    val prefix: String

    override fun provideLinks(nowSupplier: Supplier<OffsetDateTime>): List<ComicLink> {
        val today = nowSupplier.get()
        return (0 until numberOfDays)
            .flatMap { daysAgo ->
                val comicDate = today.minusDays(daysAgo.toLong())

                paths
                    .filter { path -> path.schedule.acceptDate(comicDate) }
                    .map { path ->
                        ComicLink(
                            comicDate,
                            "$prefix - ${path.name} for ${titleFormat.format(comicDate)}",
                            "${rootUrl.trimUrlSegment()}/${path.path.trimUrlSegment()}/${pathFormat.format(comicDate)}"
                        )
                    }
            }
    }
}
