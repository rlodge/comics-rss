package com.electronicmuse.comicsrss

import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.GetObjectRequest
import aws.sdk.kotlin.services.s3.model.ObjectCannedAcl
import aws.sdk.kotlin.services.s3.model.PutObjectRequest
import aws.smithy.kotlin.runtime.content.ByteStream
import aws.smithy.kotlin.runtime.content.toByteArray
import java.nio.charset.StandardCharsets

class S3FileAccess(val region: String, val bucket: String, val prefix: String, val publicRead: Boolean = false) : FileAccess {

    val s3 = S3Client { region = this@S3FileAccess.region }

    override suspend fun readData(path: String): ByteArray? {
        return s3.getObject(GetObjectRequest {
            bucket = this@S3FileAccess.bucket
            key = toFullKey(path)
        }) { resp ->
            resp.body?.toByteArray()
        }
    }

    override suspend fun writeData(path: String, bytes: ByteArray) {
        s3.putObject(PutObjectRequest {
            bucket = this@S3FileAccess.bucket
            key = toFullKey(path)
            body = ByteStream.fromBytes(bytes)
            contentLength = bytes.size.toLong()
            if (path.endsWith("xml")) {
                contentType = "application/xml"
                contentEncoding = StandardCharsets.UTF_8.name()
            }
            if (publicRead) {
                acl = ObjectCannedAcl.PublicRead
            }
        })
    }

    private fun toFullKey(path: String): String = "${this@S3FileAccess.prefix.replace("/$".toRegex(), "")}/${path.replace("^/".toRegex(), "")}"

}
