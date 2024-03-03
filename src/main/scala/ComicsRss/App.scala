package ComicsRss

;

/**
 * Hello world!
 *
 */
object App extends App {

	val configAccess: FileAccess = new FileSystemAccess()
	val outputAccess: FileAccess = new FileSystemAccess()

	ComicsLoader.loadComics(configAccess, outputAccess)

}
