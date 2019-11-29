package com.diegocunha.marvelheroguide.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegocunha.marvelheroguide.model.data.Character
import com.diegocunha.marvelheroguide.model.repository.MarvelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeViewModel(private val repository: MarvelRepository) : ViewModel() {

    companion object {
        private var LIMIT = 20
        private var OFFSET = 0
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _heroes = MutableLiveData<List<Character>>()
    val heroes: LiveData<List<Character>> = _heroes

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error


    suspend fun loadHeroes() = withContext(Dispatchers.IO) {

        _isLoading.postValue(true)

        try {
            val response = repository.getHeroes(LIMIT, OFFSET)
            if (response.isSuccessful) {
                response.body()?.let { responseBosdy ->
                    _heroes.postValue(responseBosdy.data.characters)
                }

                OFFSET += LIMIT
            } else {
                _error.postValue(true)
            }

        } catch (e: Exception) {
            _error.postValue(true)
        } finally {
            _isLoading.postValue(false)
        }
    }
}