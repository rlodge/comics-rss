package com.electronicmuse.comicsrss

data class Configuration (
    val rssOutputPath: String,
    val bucket: String,
    val prefix: String,
    val region: String,
    val providers: List<ComicLinkProvider>
)
