package com.electronicmuse.comicsrss

interface FileAccess {

    suspend fun readData(path: String): ByteArray?
    suspend fun writeData(path: String, bytes: ByteArray): Unit
}
