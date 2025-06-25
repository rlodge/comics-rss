package com.electronicmuse.comicsrss

import org.junit.jupiter.api.Test
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LambdaTest {

    @Test
    fun testLambda() {
        val lambda = Lambda()
        lambda.fileAccess = object : FileAccess {
            override suspend fun readData(path: String): ByteArray? {
                TODO("Not yet implemented")
            }

            override suspend fun writeData(path: String, bytes: ByteArray) {
                val outputFile = File("target/test-output/${DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now())}/${path}")
                outputFile.parentFile.mkdirs()
                outputFile.writeBytes(bytes)
            }

        }
        lambda.handleRequest(null, null)
    }
}