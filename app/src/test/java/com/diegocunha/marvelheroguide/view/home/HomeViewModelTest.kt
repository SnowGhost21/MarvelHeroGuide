package com.diegocunha.marvelheroguide.view.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegocunha.marvelheroguide.helper.assertLiveDataEquals
import com.diegocunha.marvelheroguide.helper.assertLiveDataNull
import com.diegocunha.marvelheroguide.model.fixture.createResponse
import com.diegocunha.marvelheroguide.model.repository.MarvelRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
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
class HomeViewModelTest {

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
    fun `Get list of Heroes`() = runBlocking {
        `when`(marvelRepository.getHeroes(any(), any())).thenReturn(Response.success(200, createResponse))
        val viewModel = HomeViewModel(marvelRepository)
        withContext(Dispatchers.IO) {
            viewModel.loadHeroes()
            assertLiveDataEquals(createResponse.data.characters, viewModel.heroes)
        }
    }

    @Test
    fun `Get list of Heroes without any Error`() = runBlocking {
        `when`(marvelRepository.getHeroes(any(), any())).thenReturn(Response.success(200, createResponse))
        val viewModel = HomeViewModel(marvelRepository)
        withContext(Dispatchers.IO) {
            viewModel.loadHeroes()
            assertLiveDataNull(viewModel.error)
        }
    }
}