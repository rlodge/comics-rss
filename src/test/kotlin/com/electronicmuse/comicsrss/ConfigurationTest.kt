package com.electronicmuse.comicsrss

import com.electronicmuse.comicsrss.providers.ComicPathAndSchedule
import com.electronicmuse.comicsrss.providers.ComicsKingdomComicLinkProvider
import com.electronicmuse.comicsrss.providers.GoComicsComicLinkProvider
import com.electronicmuse.comicsrss.providers.MadamAndEveComicLinkProvider
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ConfigurationTest {

    @Test
    fun `can be round trip serialized`() {
        val config = Configuration(
            rssOutputPath = "/comics-rss.xml",
            bucket = "electronicmuse-public",
            prefix = "comics-rss",
            region = "us-west-2",
            providers = listOf(
                ComicsKingdomComicLinkProvider(
                    "https://comicskingdom.com",
                    numberOfDays = 30,
                    paths = listOf(
                        ComicPathAndSchedule("Rex Morgan", "rex-morgan-m-d", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Sally Forth", "sally-forth", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Beetle Baily", "beetle-bailey-1", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Blondie", "blondie", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Hagar the Horrible", "hagar-the-horrible", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Hi and Lois", "hi-and-lois", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Mutts", "mutts", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Zits", "zits", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Prince Valiant", "prince-valiant", schedule = Schedule.SUNDAY),
                    )
                ),
                GoComicsComicLinkProvider(
                    "https://www.gocomics.com",
                    numberOfDays = 30,
                    paths = listOf(
                        ComicPathAndSchedule("Crankshaft", "crankshaft", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("9 Chickweed Lane", "9-chickweed-lane", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Baby Blues", "babyblues", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Baldo", "baldo", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Doonesbury", "doonesbury", schedule = Schedule.SUNDAY),
                        ComicPathAndSchedule("Garfield", "garfield", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Get Fuzzy", "getfuzzy", schedule = Schedule.SUNDAY),
                        ComicPathAndSchedule("Jump Start", "jumpstart", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Luann", "luann", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Non Sequitur", "nonsequitur", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Overboard", "overboard", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Pearls Before Swine", "pearlsbeforeswine", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Pickles", "pickles", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Pluggers", "pluggers", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Rabbits Against Magic", "rabbitsagainstmagic", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Rose is Rose", "roseisrose", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Rubes", "rubes", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Shoe", "shoe", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Stone Soup", "stonesoup", schedule = Schedule.SUNDAY),
                        ComicPathAndSchedule("Tank McNamara", "tankmcnamara", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Wizard of Id", "wizardofid", schedule = Schedule.SEVEN_DAYS),
                        ComicPathAndSchedule("Nancy", "nancy", schedule = Schedule.SEVEN_DAYS),
                    )
                ),
                MadamAndEveComicLinkProvider(
                    archivePath = "https://www.dailymaverick.co.za/section/madam-eve/"
                ),
            )
        )

        val mapper: ObjectMapper = YAMLMapper()
        mapper.findAndRegisterModules()

        val output = mapper.writeValueAsString(config)
        val configText = javaClass.classLoader.getResource("config.yaml")!!.readText()

        val actual1: Configuration = mapper.readValue(output)
        val actual2: Configuration = mapper.readValue(configText)

        assertThat(actual1).isEqualTo(config)
        assertThat(actual2).isEqualTo(config)
    }
}
