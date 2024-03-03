/**
	*
	*
	* @author Ross M. Lodge
	*/

import ComicsRss._

new ComicsConfig(
	"/var/www/comics-rss.xml",
	//"test.xml",
	ComicsKingdomComic("Funky Winkerbean", "https://comicskingdom.com/funky-winkerbean/", schedule = SevenDays),
	ComicsKingdomComic("Rex Morgan", "https://comicskingdom.com/rex-morgan-m-d/", schedule = SevenDays),
	ComicsKingdomComic("Sally Forth", "https://comicskingdom.com/sally-forth/", schedule = SevenDays),
	ComicsKingdomComic("Baby Blues", "https://comicskingdom.com/baby-blues/", schedule = SevenDays),
	ComicsKingdomComic("Beetle Baily", "https://comicskingdom.com/beetle-bailey-1/", schedule = SevenDays),
	ComicsKingdomComic("Blondie", "https://comicskingdom.com/blondie/", schedule = SevenDays),
	ComicsKingdomComic("Crankshaft", "https://comicskingdom.com/crankshaft/", schedule = SevenDays),
	ComicsKingdomComic("Hagar the Horrible", "https://comicskingdom.com/hagar-the-horrible/", schedule = SevenDays),
	ComicsKingdomComic("Hi and Lois", "https://comicskingdom.com/hi-and-lois/", schedule = SevenDays),
	ComicsKingdomComic("Mutts", "https://comicskingdom.com/mutts/", schedule = SevenDays),
	ComicsKingdomComic("Zits", "https://comicskingdom.com/zits/", schedule = SevenDays),
	ComicsKingdomComic("Prince Valiant", "https://comicskingdom.com/prince-valiant/", schedule = Sunday),
	GoComicsDotComComic("9 Chickweed Lane", "http://www.gocomics.com/9chickweedlane", schedule = SevenDays),
	GoComicsDotComComic("Baldo", "http://www.gocomics.com/baldo", schedule = SevenDays),
	GoComicsDotComComic("Doonesbury", "http://www.gocomics.com/doonesbury", schedule = Sunday),
	GoComicsDotComComic("Garfield", "http://www.gocomics.com/garfield", schedule = SevenDays),
	GoComicsDotComComic("Get Fuzzy", "http://www.gocomics.com/getfuzzy", schedule = Sunday),
	GoComicsDotComComic("Jump Start", "http://www.gocomics.com/jumpstart", schedule = SevenDays),
	GoComicsDotComComic("Luann", "http://www.gocomics.com/luann", schedule = SevenDays),
	GoComicsDotComComic("Non Sequitur", "http://www.gocomics.com/nonsequitur", schedule = SevenDays),
	GoComicsDotComComic("Overboard", "http://www.gocomics.com/overboard", schedule = SevenDays),
	GoComicsDotComComic("Pearls Before Swine", "http://www.gocomics.com/pearlsbeforeswine", schedule = SevenDays),
	GoComicsDotComComic("Pickles", "http://www.gocomics.com/pickles", schedule = SevenDays),
	GoComicsDotComComic("Pluggers", "http://www.gocomics.com/pluggers", schedule = SevenDays),
	GoComicsDotComComic("Rabbits Against Magic", "http://www.gocomics.com/rabbitsagainstmagic", schedule = SevenDays),
	GoComicsDotComComic("Rose is Rose", "http://www.gocomics.com/roseisrose", schedule = SevenDays),
	GoComicsDotComComic("Rubes", "http://www.gocomics.com/rubes", schedule = SevenDays),
	GoComicsDotComComic("Shoe", "http://www.gocomics.com/shoe", schedule = SevenDays),
	GoComicsDotComComic("Stone Soup", "http://www.gocomics.com/stonesoup", schedule = Sunday),
	GoComicsDotComComic("Tank McNamara", "http://www.gocomics.com/tankmcnamara", schedule = SevenDays),
	GoComicsDotComComic("Wizard of Id", "http://www.gocomics.com/wizardofid", schedule = SevenDays),
	GoComicsDotComComic("Bloom County 2015", "http://www.gocomics.com/bloom-county", schedule = SevenDays, Some(BackDating(new DateTime(2015, 9, 29, 0, 0), new DateTime(2015, 7, 20, 0, 0)))),
	MadamAndEveComic(30)
)
