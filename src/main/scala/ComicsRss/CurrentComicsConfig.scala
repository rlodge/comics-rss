package ComicsRss

object CurrentComicsConfig {

	def getConfig: ComicsConfig = new ComicsConfig(
		"/var/www/comics-rss.xml",
		ComicsKingdomComic("Rex Morgan", "https://v7.comicskingdom.net/comics/rex-morgan-m-d/", schedule = Schedule.SevenDays),
		ComicsKingdomComic("Sally Forth", "https://v7.comicskingdom.net/comics/sally-forth/", schedule = Schedule.SevenDays),
		ComicsKingdomComic("Beetle Baily", "https://v7.comicskingdom.net/comics/beetle-bailey-1/", schedule = Schedule.SevenDays),
		ComicsKingdomComic("Blondie", "https://v7.comicskingdom.net/comics/blondie/", schedule = Schedule.SevenDays),
		ComicsKingdomComic("Hagar the Horrible", "https://v7.comicskingdom.net/comics/hagar-the-horrible/", schedule = Schedule.SevenDays),
		ComicsKingdomComic("Hi and Lois", "https://v7.comicskingdom.net/comics/hi-and-lois/", schedule = Schedule.SevenDays),
		ComicsKingdomComic("Mutts", "https://v7.comicskingdom.net/comics/mutts/", schedule = Schedule.SevenDays),
		ComicsKingdomComic("Zits", "https://v7.comicskingdom.net/comics/zits/", schedule = Schedule.SevenDays),
		ComicsKingdomComic("Prince Valiant", "https://v7.comicskingdom.net/comics/prince-valiant/", schedule = Schedule.Sunday),
		GoComicsDotComComic("Crankshaft", "https://www.gocomics.com/crankshaft", schedule = Schedule.SevenDays),
		GoComicsDotComComic("9 Chickweed Lane", "https://www.gocomics.com/9chickweedlane", schedule = Schedule.SevenDays),
		GoComicsDotComComic("Baby Blues", "https://www.gocomics.com/babyblues", schedule = Schedule.SevenDays),
		GoComicsDotComComic("Baldo", "https://www.gocomics.com/baldo", schedule = Schedule.SevenDays),
		GoComicsDotComComic("Doonesbury", "https://www.gocomics.com/doonesbury", schedule = Schedule.Sunday),
		GoComicsDotComComic("Garfield", "https://www.gocomics.com/garfield", schedule = Schedule.SevenDays),
		GoComicsDotComComic("Get Fuzzy", "https://www.gocomics.com/getfuzzy", schedule = Schedule.Sunday),
		GoComicsDotComComic("Jump Start", "https://www.gocomics.com/jumpstart", schedule = Schedule.SevenDays),
		GoComicsDotComComic("Luann", "https://www.gocomics.com/luann", schedule = Schedule.SevenDays),
		GoComicsDotComComic("Non Sequitur", "https://www.gocomics.com/nonsequitur", schedule = Schedule.SevenDays),
		GoComicsDotComComic("Overboard", "https://www.gocomics.com/overboard", schedule = Schedule.SevenDays),
		GoComicsDotComComic("Pearls Before Swine", "https://www.gocomics.com/pearlsbeforeswine", schedule = Schedule.SevenDays),
		GoComicsDotComComic("Pickles", "https://www.gocomics.com/pickles", schedule = Schedule.SevenDays),
		GoComicsDotComComic("Pluggers", "https://www.gocomics.com/pluggers", schedule = Schedule.SevenDays),
		GoComicsDotComComic("Rabbits Against Magic", "https://www.gocomics.com/rabbitsagainstmagic", schedule = Schedule.SevenDays),
		GoComicsDotComComic("Rose is Rose", "https://www.gocomics.com/roseisrose", schedule = Schedule.SevenDays),
		GoComicsDotComComic("Rubes", "https://www.gocomics.com/rubes", schedule = Schedule.SevenDays),
		GoComicsDotComComic("Shoe", "https://www.gocomics.com/shoe", schedule = Schedule.SevenDays),
		GoComicsDotComComic("Stone Soup", "https://www.gocomics.com/stonesoup", schedule = Schedule.Sunday),
		GoComicsDotComComic("Tank McNamara", "https://www.gocomics.com/tankmcnamara", schedule = Schedule.SevenDays),
		GoComicsDotComComic("Wizard of Id", "https://www.gocomics.com/wizardofid", schedule = Schedule.SevenDays),
		MadamAndEveComic(),
	)

}
