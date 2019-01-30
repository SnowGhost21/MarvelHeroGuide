package com.diegocunha.marvelheroguide.model.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class Response(
        val data: Data
)

data class Data(
        val offset: Int,
        val limit: Int,
        val count: Int,
        @SerializedName("results") var characters: List<Character> = emptyList()
)

data class Character(
        val id: Int?, val name: String?, val description: String?,
        val modified: Date?, val resourceUri: String?, val urls: Array<Url>?,
        val thumbnail: Image?, val comics: ComicList?, val stories: StoryList?,
        val events: EventList?, val series: SeriesList?
)

class Image(val path: String, val extension: String)

class Url(val type: String?, val url: String?)

class ComicList(
        val available: Int?, val returned: Int?, val collectionURI: String?,
        val items: Array<ComicSummary>?
)

class ComicSummary(val resourceURI: String?, val name: String?)

class StoryList(
        val available: Int?, val returned: Int?, val collectionURI: String?,
        val items: Array<StorySummary>?
)

class StorySummary(val resourceURI: String?, val name: String?, val type: String?)

class EventList(
        val available: Int?, val returned: Int?, val collectionURI: String?,
        val items: Array<EventSummary>?
)

class EventSummary(val resourceURI: String?, val name: String?)

class SeriesList(
        val available: Int?, val returned: Int?, val collectionURI: String?,
        val items: Array<SeriesSummary>?
)

class SeriesSummary(val resourceURI: String?, val name: String?)