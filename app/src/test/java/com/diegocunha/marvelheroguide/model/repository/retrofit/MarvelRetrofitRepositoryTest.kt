package com.diegocunha.marvelheroguide.model.repository.retrofit

import com.diegocunha.marvelheroguide.model.fixture.createResponse
import com.diegocunha.marvelheroguide.model.fixture.createResponseComic
import com.diegocunha.marvelheroguide.model.fixture.createResponseCreator
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MarvelRetrofitRepositoryTest {

    private val api = mock<MarvelApi>()

    @Before
    fun setup() {
        whenever(api.getHeroes(any())).thenReturn(Single.just(createResponse))
        whenever(api.getHeroById(any(), any())).thenReturn(Single.just(createResponse))
        whenever(api.getComicsByHeroId(any(), any())).thenReturn(Single.just(createResponseComic))
        whenever(api.getComicById(any(), any())).thenReturn(Single.just(createResponseComic))
        whenever(api.getCreatorsByComicId(any(), any())).thenReturn(Single.just(createResponseCreator))
    }

    @Test
    fun shouldGetHeroesFromApi() {
        val repository = MarvelRetrofitRepository(api)
        repository.getHeroes(20, 0)
        verify(api).getHeroes(any())
    }

    @Test
    fun shouldGetHeroByIdFromApi() {
        val repository = MarvelRetrofitRepository(api)
        repository.getHeroById(1234)
        verify(api).getHeroById(any(), any())
    }

    @Test
    fun shouldGetComicsByHeroIdFromApi() {
        val repository = MarvelRetrofitRepository(api)
        repository.getComicsByHeroId(1234)
        verify(api).getComicsByHeroId(any(), any())
    }

    @Test
    fun shouldGetComicByIdFromApi() {
        val repository = MarvelRetrofitRepository(api)
        repository.getComicById(1234)
        verify(api).getComicById(any(), any())
    }

    @Test
    fun shouldGetListCreatorsByComicIdFromApi() {
        val repository = MarvelRetrofitRepository(api)
        repository.getCreatorsByComicId(1234)
        verify(api).getCreatorsByComicId(any(), any())
    }

}