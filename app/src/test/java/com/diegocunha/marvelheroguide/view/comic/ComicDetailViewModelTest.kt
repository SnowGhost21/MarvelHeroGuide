package com.diegocunha.marvelheroguide.view.comic

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegocunha.marvelheroguide.assertLiveDataEquals
import com.diegocunha.marvelheroguide.model.fixture.createListComicSummary
import com.diegocunha.marvelheroguide.model.fixture.createResponseComic
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
class ComicDetailViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val marvelRepository = mock<MarvelRepository>()

    @Before
    fun setup() {
        whenever(marvelRepository.getComicById(any())).thenReturn(Single.just(createResponseComic))
    }

    @Test
    fun shouldGetComicDetail() {
        val viewModel = ComicDetailViewModel(marvelRepository, 1234)
        assertLiveDataEquals(createListComicSummary.first(), viewModel.comic)
    }

    @Test
    fun shouldGetComicDescription() {
        val viewModel = ComicDetailViewModel(marvelRepository, 1234)
        val description = createListComicSummary.first().description
        assertLiveDataEquals(description, viewModel.description)
    }

    @Test
    fun shouldGetComicImage() {
        val viewModel = ComicDetailViewModel(marvelRepository, 1234)
        val image = createListComicSummary.first().thumbnail
        assertLiveDataEquals(image, viewModel.comicImage)
    }
}