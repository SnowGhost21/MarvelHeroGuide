package com.diegocunha.marvelheroguide.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegocunha.marvelheroguide.extensions.asLiveData
import com.diegocunha.marvelheroguide.model.data.Character
import com.diegocunha.marvelheroguide.model.repository.MarvelRepository
import io.reactivex.disposables.Disposable

class HomeViewModel(private val repository: MarvelRepository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val heroes: LiveData<List<Character>> = repository.getHeroes(20, 0)
            .map { it.data.characters }
            .asLiveData()


    private var requestHeroes: Disposable? = null

    override fun onCleared() {
        super.onCleared()
        requestHeroes?.dispose()
    }
}