package ComcsRss

import ComicsRss.{ComicLink, GoComicsDotComComic, GoComicsDotComPageReader}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, FunSpec}

/**
 *
 *
 * @author Ross M. Lodge
 */
@RunWith(classOf[JUnitRunner])
class GoComicsDotComPageReaderTest extends FunSpec with Matchers {

	describe("A GoComicsDotComPageReader") {
		it("should return up to today") {
			val links: List[ComicLink] = GoComicsDotComPageReader(GoComicsDotComComic("foo", "http://foo.com")).render()
			links should have(size(10))
			links.last.dateTime should be === new DateTime().withTimeAtStartOfDay()
		}
	}
}
