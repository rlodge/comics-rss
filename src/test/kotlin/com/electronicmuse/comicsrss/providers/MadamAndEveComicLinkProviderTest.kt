package com.electronicmuse.comicsrss.providers

import org.junit.jupiter.api.Test
import java.time.OffsetDateTime
import java.time.ZoneOffset

class MadamAndEveComicLinkProviderTest {

    @Test
    fun `should provide expected links from static document`() {
        val provider = MadamAndEveComicLinkProvider("src/test/resources/sample-madam-and-eve-page.html")
        val now = OffsetDateTime.of(2023, 7, 8, 13, 22, 48, 0, ZoneOffset.UTC)
        val links = provider.provideLinks({ now } )
        links.forEach { println(it) }
    }
}