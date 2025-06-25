package com.electronicmuse.comicsrss.providers

import com.electronicmuse.comicsrss.ComicLink
import com.electronicmuse.comicsrss.Schedule
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.*

class GoComicsComicLinkProviderTest {

    @Test
    fun testGetLinks() {
        val provider = GoComicsComicLinkProvider(
            "https://example.com/comics",
            14,
            listOf(
                ComicPathAndSchedule("EveryDay", "/everyday", Schedule.SEVEN_DAYS),
                ComicPathAndSchedule("Sundays", "sunday/", Schedule.SUNDAY),
            )
        )
        val now = ZonedDateTime.of(LocalDate.of(2025, Month.JUNE, 25), LocalTime.of(8, 38), ZoneId.of("America/Los_Angeles")).toOffsetDateTime()
        assertThat(provider.provideLinks { now }).containsExactlyInAnyOrder(
            ComicLink(now, "GC - EveryDay for Wednesday, 2025 25 June", "https://example.com/comics/everyday/2025/06/25"),
            ComicLink(now.minusDays(1), "GC - EveryDay for Tuesday, 2025 24 June", "https://example.com/comics/everyday/2025/06/24"),
            ComicLink(now.minusDays(2), "GC - EveryDay for Monday, 2025 23 June", "https://example.com/comics/everyday/2025/06/23"),
            ComicLink(now.minusDays(3), "GC - EveryDay for Sunday, 2025 22 June", "https://example.com/comics/everyday/2025/06/22"),
            ComicLink(now.minusDays(4), "GC - EveryDay for Saturday, 2025 21 June", "https://example.com/comics/everyday/2025/06/21"),
            ComicLink(now.minusDays(5), "GC - EveryDay for Friday, 2025 20 June", "https://example.com/comics/everyday/2025/06/20"),
            ComicLink(now.minusDays(6), "GC - EveryDay for Thursday, 2025 19 June", "https://example.com/comics/everyday/2025/06/19"),
            ComicLink(now.minusDays(7), "GC - EveryDay for Wednesday, 2025 18 June", "https://example.com/comics/everyday/2025/06/18"),
            ComicLink(now.minusDays(8), "GC - EveryDay for Tuesday, 2025 17 June", "https://example.com/comics/everyday/2025/06/17"),
            ComicLink(now.minusDays(9), "GC - EveryDay for Monday, 2025 16 June", "https://example.com/comics/everyday/2025/06/16"),
            ComicLink(now.minusDays(10), "GC - EveryDay for Sunday, 2025 15 June", "https://example.com/comics/everyday/2025/06/15"),
            ComicLink(now.minusDays(11), "GC - EveryDay for Saturday, 2025 14 June", "https://example.com/comics/everyday/2025/06/14"),
            ComicLink(now.minusDays(12), "GC - EveryDay for Friday, 2025 13 June", "https://example.com/comics/everyday/2025/06/13"),
            ComicLink(now.minusDays(13), "GC - EveryDay for Thursday, 2025 12 June", "https://example.com/comics/everyday/2025/06/12"),
            ComicLink(now.minusDays(3), "GC - Sundays for Sunday, 2025 22 June", "https://example.com/comics/sunday/2025/06/22"),
            ComicLink(now.minusDays(10), "GC - Sundays for Sunday, 2025 15 June", "https://example.com/comics/sunday/2025/06/15"),
        )
    }
}
