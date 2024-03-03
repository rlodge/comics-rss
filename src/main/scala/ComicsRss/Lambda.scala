package ComicsRss

import com.amazonaws.services.lambda.runtime.Context

/**
	*
	*
	* @author Ross M. Lodge
	*/
class Lambda {

	def downloadComics(input: AnyRef, context: Context): String = {
		val configAccess: FileAccess = new S3Access("electronicmuse-config", "comics-rss")
		val outputAccess: FileAccess = new S3Access("electronicmuse-public", "comics-rss", true)
		ComicsLoader.loadComics(configAccess, outputAccess)
		"done"
	}

}
