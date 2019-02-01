package com.diegocunha.marvelheroguide.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegocunha.marvelheroguide.extensions.asLiveData
import com.diegocunha.marvelheroguide.model.data.Character
import com.diegocunha.marvelheroguide.model.repository.MarvelRepository
import io.reactivex.disposables.Disposable

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

    init {
        loadHeroes()
    }

    fun loadHeroes() {
        repository.getHeroes(LIMIT, OFFSET)
                .map { it.data.characters }
                .doOnError { _error.postValue(true) }
                .doOnSuccess { _heroes.postValue(it) }
                .doOnSubscribe { _isLoading.postValue(true) }
                .doAfterTerminate {
                    OFFSET += LIMIT
                    _isLoading.postValue(false)
                }.asLiveData()

    }
}