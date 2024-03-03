package ComicsRss

import java.io.{ByteArrayInputStream, File, FileInputStream, InputStream}
import java.nio.file.{Files, StandardOpenOption}

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.{CannedAccessControlList, ObjectMetadata, PutObjectRequest}

/**
	*
	*
	* @author Ross M. Lodge
	*/
trait FileAccess {

	def readData(location: String): InputStream

	def writeData(location: String, data: Array[Byte]): Unit

	def dataExists(location: String): Boolean

}

class FileSystemAccess extends FileAccess {
	override def readData(location: String): InputStream = new FileInputStream(location)
	override def writeData(location: String, data: Array[Byte]): Unit = Files.write(new File(location).toPath, data, StandardOpenOption.CREATE, StandardOpenOption.WRITE)
	override def dataExists(location: String): Boolean = new File(location).exists()
}

class S3Access(bucket: String, prefix: String, public: Boolean = false) extends FileAccess {

	val client = new AmazonS3Client(new DefaultAWSCredentialsProviderChain())

	override def readData(location: String): InputStream = {
		val obj = client.getObject(bucket, s"$prefix/${location.replaceAll(""".*/""","")}")
		obj.getObjectContent
	}

	override def writeData(location: String, data: Array[Byte]): Unit = {
		val metadata = new ObjectMetadata()
		metadata.setContentLength(data.length)
		metadata.setContentEncoding("UTF-8")
		if (location.endsWith("xml")) {
			metadata.setContentType("application/xml")
		}
		val putObjectRequest = new PutObjectRequest(
			bucket,
			s"$prefix/${location.replaceAll(""".*/""","")}",
			new ByteArrayInputStream(data),
			metadata
		)
		if (public) {
			client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead))
		} else {
			client.putObject(putObjectRequest)
		}
	}
	override def dataExists(location: String): Boolean = client.doesObjectExist(bucket, s"$prefix/${location.replaceAll(""".*/""","")}")
}
