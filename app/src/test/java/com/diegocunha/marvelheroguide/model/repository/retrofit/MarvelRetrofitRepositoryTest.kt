package com.diegocunha.marvelheroguide.model.repository.retrofit

import com.diegocunha.marvelheroguide.model.fixture.createFixtureDefaultParameters
import com.diegocunha.marvelheroguide.model.fixture.createResponse
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
    }

    @Test
    fun shouldGetHeroesFromApi() {
        val repository = MarvelRetrofitRepository(api)
        repository.getHeroes(20, 0)
        verify(api).getHeroes(any())
    }

}