package com.electronicmuse.comicsrss

import java.time.OffsetDateTime

data class ComicLink(val date: OffsetDateTime, val title: String, val link: String, val description: String? = null)
