package com.electronicmuse.comicsrss

import java.io.InputStream

interface FileAccess {

    suspend fun readData(path: String): ByteArray?
    suspend fun writeData(path: String, bytes: ByteArray): Unit
}
