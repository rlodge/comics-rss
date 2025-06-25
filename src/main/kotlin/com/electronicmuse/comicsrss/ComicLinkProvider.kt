package com.electronicmuse.comicsrss

import com.electronicmuse.comicsrss.providers.ComicsKingdomComicLinkProvider
import com.electronicmuse.comicsrss.providers.GoComicsComicLinkProvider
import com.electronicmuse.comicsrss.providers.MadamAndEveComicLinkProvider
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.time.OffsetDateTime
import java.util.function.Supplier

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = ComicsKingdomComicLinkProvider::class, name = "comics-kingdom"),
    JsonSubTypes.Type(value = GoComicsComicLinkProvider::class, name = "go-comics"),
    JsonSubTypes.Type(value = MadamAndEveComicLinkProvider::class, name = "madam-and-eve"),
)
interface ComicLinkProvider {

    fun provideLinks(nowSupplier: Supplier<OffsetDateTime> = Supplier { OffsetDateTime.now() }): List<ComicLink>
}
