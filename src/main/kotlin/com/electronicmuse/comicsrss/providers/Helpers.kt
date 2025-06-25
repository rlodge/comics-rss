package com.electronicmuse.comicsrss.providers

fun String.trimUrlSegment(): String = this.trimStart('/').trimEnd('/')
