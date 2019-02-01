package com.diegocunha.marvelheroguide.model.fixture

import com.diegocunha.marvelheroguide.model.data.*
import java.util.*


val createUrl = Url("type", "url")

val createListUrl = listOf(createUrl)

val createImage = Image("path", ".png")

val createCreatorSummary = CreatorSummary(0, "fullName", createImage)

val createListOfCreators = listOf(createCreatorSummary)

val createCreator = Creator(createListOfCreators)

val createComicSummary = ComicSummary(
        0,
        0,
        "title",
        "description",
        createImage,
        "resourceId",
        "name",
        createCreator
)

val createDataCreator = DataCreator(createListOfCreators)

val createResponseCreator = ResponseCreator(createDataCreator)

val createListComicSummary = listOf(createComicSummary)

val createComics = Comic(0, 0, "uri", createListComicSummary)

val createStorySummary = StorySummary("uri", "name", "type")

val createListStorySummary = listOf(createStorySummary)

val createStory = Story(0, 0, "uri", createListStorySummary)

val createEventSummary = EventSummary("uri", "name")

val createEventListSummary = listOf(createEventSummary)

val createEvent = Event(0, 0, "uri", createEventListSummary)

val createSeriesSummary = SeriesSummary("uri", "name")

val createSeriestListSummary = listOf(createSeriesSummary)

val createSerie = Serie(0, 0, "uri", createSeriestListSummary)

val createCharacter = Character(
        0,
        "Iron Man",
        "The Golden Avenger",
        Date(),
        "resourceUri",
        createListUrl,
        createImage,
        createComics,
        createStory,
        createEvent,
        createSerie)

val createListCharacter = listOf<Character>(createCharacter)

val createData = Data(0, 0, 0, createListCharacter)

val createResponse = Response(createData)

val createComicData = DataComic(createListComicSummary)

val createResponseComic = ResponseComic(createComicData)

