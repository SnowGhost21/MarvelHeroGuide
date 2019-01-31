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
        val modified: Date?, val resourceUri: String?, val urls: List<Url>?,
        val thumbnail: Image?, val comics: Comic?, val stories: Story?,
        val events: Event?, val series: Serie?
)

class Image(val path: String, val extension: String)

class Url(val type: String?, val url: String?)

class Comic(
        val available: Int?, val returned: Int?, val collectionURI: String?,
        val items: List<ComicSummary>?
)

class ComicSummary(val resourceURI: String?, val name: String?)

class Story(
        val available: Int?, val returned: Int?, val collectionURI: String?,
        val items: List<StorySummary>?
)

class StorySummary(val resourceURI: String?, val name: String?, val type: String?)

class Event(
        val available: Int?, val returned: Int?, val collectionURI: String?,
        val items: List<EventSummary>?
)

class EventSummary(val resourceURI: String?, val name: String?)

class Serie(
        val available: Int?, val returned: Int?, val collectionURI: String?,
        val items: List<SeriesSummary>?
)

class SeriesSummary(val resourceURI: String?, val name: String?)