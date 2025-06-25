package com.electronicmuse.comicsrss.providers

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.format.DateTimeFormatter

data class ComicsKingdomComicLinkProvider(
    override val rootUrl: String,
    override val numberOfDays: Int,
    override val paths: List<ComicPathAndSchedule>,
) : StaticComicLinkProvider {
    @JsonIgnore
    override val titleFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, yyyy dd MMMM")
    @JsonIgnore
    override val pathFormat: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE
    @JsonIgnore
    override val prefix = "CK"
}
