package com.diegocunha.marvelheroguide.model.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class Response(
        val data: Data
)

data class ResponseComic(
        val data: DataComic
)

data class ResponseCreator(
        val data: DataCreator
)

data class DataCreator(
        @SerializedName("results")val creators: List<CreatorSummary>)

data class DataComic(
        @SerializedName("results") val comics: List<ComicSummary>
)

data class Data(
        val offset: Int,
        val limit: Int,
        val count: Int,
        @SerializedName("results") var characters: List<Character> = emptyList()
)

data class Character(
        val id: Int, val name: String, val description: String?,
        val modified: Date?, val resourceUri: String?, val urls: List<Url>?,
        val thumbnail: Image?, val comics: Comic?, val stories: Story?,
        val events: Event?, val series: Serie?
)

data class Image(val path: String, val extension: String)

data class Url(val type: String?, val url: String?)

data class Comic(
        val available: Int?, val returned: Int?, val collectionURI: String?,
        var items: List<ComicSummary>?
)

data class ComicSummary(val id: Int,
                        val digitalId: Int,
                        val title: String,
                        val description: String?,
                        val thumbnail: Image?,
                        val resourceURI: String?,
                        val name: String?,
                        val creators: Creator)

data class Story(
        val available: Int?, val returned: Int?, val collectionURI: String?,
        val items: List<StorySummary>?
)

data class StorySummary(val resourceURI: String?, val name: String?, val type: String?)

data class Event(
        val available: Int?, val returned: Int?, val collectionURI: String?,
        val items: List<EventSummary>?
)

data class EventSummary(val resourceURI: String?, val name: String?)

data class Serie(
        val available: Int?, val returned: Int?, val collectionURI: String?,
        val items: List<SeriesSummary>?
)

data class SeriesSummary(val resourceURI: String?, val name: String?)

data class Creator(@SerializedName("items") var creators: List<CreatorSummary>?)

data class CreatorSummary(val id: Int,
                          val fullName: String,
                          val thumbnail: Image?)