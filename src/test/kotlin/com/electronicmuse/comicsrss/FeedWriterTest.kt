package com.electronicmuse.comicsrss

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

class FeedWriterTest {

    @Test
    fun `should write correct xml`() {
        val now = Date.from(OffsetDateTime.of(2020, 1, 1, 1, 57, 49, 0, ZoneOffset.UTC).toInstant())
        val now2 = OffsetDateTime.of(2019, 12, 1, 1, 57, 49, 0, ZoneOffset.UTC)

        val xml = FeedWriter { now }.writeComics(
            listOf(
                ComicLink(now2, "Some Comic & Something", "https://foo.bar", "Description")
            )
        )

        val strippedXml = xml.replace("""\s+.*dc:date.*""".toRegex(), "")
        assertThat(strippedXml).isEqualTo("""
            <?xml version="1.0" encoding="UTF-8"?>
            <rss xmlns:dc="http://purl.org/dc/elements/1.1/" version="2.0">
              <channel>
                <title>Ross's Comics Subscriptions</title>
                <link>https://www.electronicmuse.com</link>
                <description>Links to various comics that don't have their own RSS feeds.</description>
                <pubDate>Wed, 01 Jan 2020 01:57:49 GMT</pubDate>
                <lastBuildDate>Wed, 01 Jan 2020 01:57:49 GMT</lastBuildDate>
                <ttl>120</ttl>
                <item>
                  <title>Some Comic &amp; Something</title>
                  <link>https://foo.bar</link>
                  <description>Description</description>
                  <pubDate>Sun, 01 Dec 2019 01:57:49 GMT</pubDate>
                  <guid>XD7y6s7HCQIRpb4gnTeMyZpIDCr2XwXDpCWUo0qrtq8</guid>
                </item>
              </channel>
            </rss>
        """.trimIndent())
    }

    @Test
    fun `should write correct xml without description`() {
        val now = Date.from(OffsetDateTime.of(2020, 1, 1, 1, 57, 49, 0, ZoneOffset.UTC).toInstant())
        val now2 = OffsetDateTime.of(2019, 12, 1, 1, 57, 49, 0, ZoneOffset.UTC)

        val xml = FeedWriter { now }.writeComics(
            listOf(
                ComicLink(now2, "Some Comic", "https://foo.bar")
            )
        )

        val strippedXml = xml.replace("""\s+.*dc:date.*""".toRegex(), "")
        assertThat(strippedXml).isEqualTo("""
            <?xml version="1.0" encoding="UTF-8"?>
            <rss xmlns:dc="http://purl.org/dc/elements/1.1/" version="2.0">
              <channel>
                <title>Ross's Comics Subscriptions</title>
                <link>https://www.electronicmuse.com</link>
                <description>Links to various comics that don't have their own RSS feeds.</description>
                <pubDate>Wed, 01 Jan 2020 01:57:49 GMT</pubDate>
                <lastBuildDate>Wed, 01 Jan 2020 01:57:49 GMT</lastBuildDate>
                <ttl>120</ttl>
                <item>
                  <title>Some Comic</title>
                  <link>https://foo.bar</link>
                  <pubDate>Sun, 01 Dec 2019 01:57:49 GMT</pubDate>
                  <guid>XD7y6s7HCQIRpb4gnTeMyZpIDCr2XwXDpCWUo0qrtq8</guid>
                </item>
              </channel>
            </rss>
        """.trimIndent())
    }
}