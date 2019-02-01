package com.diegocunha.marvelheroguide.view.comic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diegocunha.marvelheroguide.extensions.asLiveData
import com.diegocunha.marvelheroguide.extensions.combineWith
import com.diegocunha.marvelheroguide.extensions.map
import com.diegocunha.marvelheroguide.model.repository.MarvelRepository

class ComicDetailViewModel(private val repository: MarvelRepository,
                           private val comicId: Int) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val comic = repository.getComicById(comicId)
            .map { it.data.comics.first() }
            .doOnSubscribe { _isLoading.postValue(true) }
            .doAfterTerminate { _isLoading.postValue(false) }
            .doOnSuccess { _isLoading.postValue(false) }
            .asLiveData()
            .combineWith(
                    repository.getCreatorsByComicId(comicId)
                            .asLiveData()
            ) { comic, creators ->
                comic?.creators?.creators = creators?.data?.creators
                comic
            }

    val comicImage = comic.map { it?.thumbnail }

    val description = comic.map { it?.description }

    val creators = comic.map { it?.creators?.creators }


    class Factory(val repository: MarvelRepository) : ViewModelProvider.Factory {
        private var comicId: Int = -1

        public fun setComicId(id: Int) {
            comicId = id
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ComicDetailViewModel(repository, comicId) as T
        }
    }
}