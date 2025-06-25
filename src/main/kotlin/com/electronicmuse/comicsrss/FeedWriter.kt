package com.electronicmuse.comicsrss

import com.rometools.rome.feed.rss.Channel
import com.rometools.rome.feed.synd.SyndContentImpl
import com.rometools.rome.feed.synd.SyndEntryImpl
import com.rometools.rome.feed.synd.SyndFeedImpl
import com.rometools.rome.io.WireFeedOutput
import java.util.*
import java.util.function.Supplier

class FeedWriter(val currentDateSupplier: Supplier<Date> = Supplier { Date() }) {

    fun writeComics(comics: List<ComicLink>): String {

        val md = java.security.MessageDigest.getInstance("SHA-256")
        val enc = Base64.getUrlEncoder().withoutPadding()

        val feed = SyndFeedImpl()
        feed.feedType = "rss_2.0"
        feed.setTitle("Ross's Comics Subscriptions")
        feed.setDescription("Links to various comics that don't have their own RSS feeds.")
        feed.link = "https://www.electronicmuse.com"
        feed.publishedDate = currentDateSupplier.get()

        comics.forEach { comic ->
            val entry = SyndEntryImpl()
            entry.title = comic.title
            comic.description?.let {
                entry.description = SyndContentImpl()
                entry.description.value = it
            }
            entry.link = comic.link
            entry.publishedDate = Date.from(comic.date.toInstant())
            feed.entries.add(entry)
        }

        val wireFeed = feed.createWireFeed() as Channel

        wireFeed.ttl = 120
        wireFeed.lastBuildDate = currentDateSupplier.get()
        wireFeed.items.forEach { item ->
            item.guid.value = enc.encodeToString(md.digest(item.link.toByteArray()))
        }

        return WireFeedOutput().outputString(wireFeed).replace("\r\n", "\n").trim()
    }
}
