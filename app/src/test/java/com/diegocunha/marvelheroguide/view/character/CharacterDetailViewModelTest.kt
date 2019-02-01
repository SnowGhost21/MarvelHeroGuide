package com.diegocunha.marvelheroguide.view.character

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegocunha.marvelheroguide.assertLiveDataEquals
import com.diegocunha.marvelheroguide.model.fixture.*
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
class CharacterDetailViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val marvelRepository = mock<MarvelRepository>()

    @Before
    fun setup() {
        whenever(marvelRepository.getHeroById(any())).thenReturn(Single.just(createResponse))
        whenever(marvelRepository.getComicsByHeroId(any())).thenReturn(Single.just(createResponseComic))
    }

    @Test
    fun shouldGetHeroWithComics() {
        val viewModel = CharacterDetailViewModel(marvelRepository, 123)
        assertLiveDataEquals(createListCharacter.first(), viewModel.character)
    }

    @Test
    fun shouldGetHeroImage() {
        val viewModel = CharacterDetailViewModel(marvelRepository, 123)
        assertLiveDataEquals(createImage, viewModel.imageUrl)
    }

    @Test
    fun shouldGetComics() {
        val viewModel = CharacterDetailViewModel(marvelRepository, 123)
        assertLiveDataEquals(createCharacter.comics?.items, viewModel.comics)
    }

    @Test
    fun shouldGetDescription() {
        val viewModel = CharacterDetailViewModel(marvelRepository, 123)
        assertLiveDataEquals(createCharacter.description, viewModel.description)
    }
}