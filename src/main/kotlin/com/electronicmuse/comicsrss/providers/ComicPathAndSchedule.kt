package com.electronicmuse.comicsrss.providers

import com.electronicmuse.comicsrss.Schedule

data class ComicPathAndSchedule(
    val name: String,
    val path: String,
    val schedule: Schedule,
)
