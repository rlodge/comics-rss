package com.electronicmuse.comicsrss.providers

import com.electronicmuse.comicsrss.ComicLink
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime

class MadamAndEveComicLinkProviderTest {

    @Test
    fun `should provide expected links from static document`() {
        val provider = MadamAndEveComicLinkProvider("src/test/resources/sample-madam-and-eve-page.html")
        val now = OffsetDateTime.of(2023, 7, 8, 13, 22, 48, 0, ZoneOffset.UTC)
        val links = provider.provideLinks({ now })
        assertThat(links).isEqualTo(
            listOf(
                ComicLink(getOdt(2025, 6, 19), "M&E - Theres an american on my stoep", "https://www.dailymaverick.co.za/article/2025-06-19-theres-an-american-on-my-stoep/"),
                ComicLink(getOdt(2025, 6, 13), "M&E - Mirror mirror on the wall", "https://www.dailymaverick.co.za/article/2025-06-13-mirror-mirror-on-the-wall/"),
                ComicLink(getOdt(2025, 6, 6), "M&E - Shut the eff up", "https://www.dailymaverick.co.za/article/2025-06-06-shut-the-eff-up/"),
                ComicLink(getOdt(2025, 5, 31), "M&E - Like father like son", "https://www.dailymaverick.co.za/article/2025-05-31-like-father-like-son/"),
                ComicLink(getOdt(2025, 5, 23), "M&E - Bird force one", "https://www.dailymaverick.co.za/article/2025-05-23-bird-force-one/"),
                ComicLink(getOdt(2025, 5, 16), "M&E - Persecution complex", "https://www.dailymaverick.co.za/article/2025-05-16-persecution-complex/"),
                ComicLink(getOdt(2025, 5, 9), "M&E - Classic alarms", "https://www.dailymaverick.co.za/article/2025-05-09-classic-alarms/"),
                ComicLink(getOdt(2025, 5, 2), "M&E - School derangement syndrome", "https://www.dailymaverick.co.za/article/2025-05-02-school-derangement-syndrome/"),
                ComicLink(getOdt(2025, 4, 25), "M&E - The potholes strike back", "https://www.dailymaverick.co.za/article/2025-04-25-the-potholes-strike-back/"),
                ComicLink(getOdt(2025, 4, 17), "M&E - Love my easter egg tender", "https://www.dailymaverick.co.za/article/2025-04-17-love-my-easter-egg-tender/"),
                ComicLink(getOdt(2025, 4, 10), "M&E - The trump tariff handicap", "https://www.dailymaverick.co.za/article/2025-04-10-the-trump-tariff-handicap/"),
                ComicLink(getOdt(2025, 4, 4), "M&E - Talk the line", "https://www.dailymaverick.co.za/article/2025-04-04-talk-the-line/"),
                ComicLink(getOdt(2025, 3, 28), "M&E - Less is less", "https://www.dailymaverick.co.za/article/2025-03-28-less-is-less/"),
                ComicLink(getOdt(2025, 3, 13), "M&E - The first cut is the dumbest", "https://www.dailymaverick.co.za/article/2025-03-13-the-first-cut-is-the-dumbest/"),
                ComicLink(getOdt(2025, 3, 7), "M&E - The crack of doom scrolling", "https://www.dailymaverick.co.za/article/2025-03-07-the-crack-of-doom-scrolling/"),
                ComicLink(getOdt(2025, 2, 28), "M&E - Take five", "https://www.dailymaverick.co.za/article/2025-02-28-take-five/"),
                ComicLink(getOdt(2025, 2, 21), "M&E - I spy with my little eye", "https://www.dailymaverick.co.za/article/2025-02-21-i-spy-with-my-little-eye/"),
                ComicLink(getOdt(2025, 2, 14), "M&E - The lyin king", "https://www.dailymaverick.co.za/article/2025-02-14-the-lyin-king/"),
                ComicLink(getOdt(2025, 2, 7), "M&E - Let me sleep before you gogo", "https://www.dailymaverick.co.za/article/2025-02-07-let-me-sleep-before-you-gogo/"),
                ComicLink(getOdt(2025, 1, 31), "M&E - Expropriation nation", "https://www.dailymaverick.co.za/article/2025-01-31-expropriation-nation/"),
                ComicLink(getOdt(2025, 1, 10), "M&E - Viva la resolution", "https://www.dailymaverick.co.za/article/2025-01-10-viva-la-resolution/"),
            )
        )
    }

    private fun getOdt(year: Int, month: Int, day: Int): OffsetDateTime {
        return ZonedDateTime.of(LocalDate.of(year, month, day), LocalTime.of(0, 0), ZoneId.of("Africa/Johannesburg")).toOffsetDateTime()
    }
}