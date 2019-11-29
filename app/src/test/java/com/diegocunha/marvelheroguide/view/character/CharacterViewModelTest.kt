package com.diegocunha.marvelheroguide.view.character

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegocunha.marvelheroguide.helper.assertLiveDataEquals
import com.diegocunha.marvelheroguide.model.fixture.createListCharacter
import com.diegocunha.marvelheroguide.model.fixture.createResponse
import com.diegocunha.marvelheroguide.model.fixture.createResponseComic
import com.diegocunha.marvelheroguide.model.repository.MarvelRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()


    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val marvelRepository = mock<MarvelRepository>()


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Get the Hero details`() = runBlocking {
        `when`(marvelRepository.getHeroById(any())).thenReturn(Response.success(200, createResponse))
        `when`(marvelRepository.getComicsByHeroId(any())).thenReturn(Response.success(200, createResponseComic))
        val viewModel = CharacterDetailViewModel(marvelRepository, 123)
        viewModel.getHeroById()

        assertLiveDataEquals(createListCharacter.first(), viewModel.character)
    }




}