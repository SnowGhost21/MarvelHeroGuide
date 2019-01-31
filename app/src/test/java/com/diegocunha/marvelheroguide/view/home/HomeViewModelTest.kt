package com.diegocunha.marvelheroguide.view.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegocunha.marvelheroguide.assertLiveDataEquals
import com.diegocunha.marvelheroguide.model.fixture.createListCharacter
import com.diegocunha.marvelheroguide.model.fixture.createResponse
import com.diegocunha.marvelheroguide.model.repository.MarvelRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val marvelRepository = mock<MarvelRepository>()

    @Before
    fun setup() {
        whenever(marvelRepository.getHeroes(any(), any())).thenReturn(Single.just(createResponse))
    }

    @Test
    fun shouldGetListOfCharacters() {
        val viewModel = HomeViewModel(marvelRepository)
        assertLiveDataEquals(createListCharacter, viewModel.heroes)
    }
}