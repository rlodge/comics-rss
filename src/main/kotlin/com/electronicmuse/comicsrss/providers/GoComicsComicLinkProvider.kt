package com.electronicmuse.comicsrss.providers

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.format.DateTimeFormatter

data class GoComicsComicLinkProvider(
    override val rootUrl: String,
    override val numberOfDays: Int,
    override val paths: List<ComicPathAndSchedule>,
) : StaticComicLinkProvider {
    @JsonIgnore
    override val titleFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, yyyy dd MMMM")
    @JsonIgnore
    override val pathFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
    @JsonIgnore
    override val prefix = "GC"
}
