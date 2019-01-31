package com.diegocunha.marvelheroguide.view.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diegocunha.marvelheroguide.extensions.asLiveData
import com.diegocunha.marvelheroguide.extensions.combineWith
import com.diegocunha.marvelheroguide.extensions.map
import com.diegocunha.marvelheroguide.model.repository.MarvelRepository

class CharacterDetailViewModel(private val repository: MarvelRepository,
                               private val characterId: Int) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    val character = repository.getHeroById(characterId)
            .map { it.data.characters.first() }
            .doOnSubscribe { _isLoading.postValue(true) }
            .asLiveData()
            .combineWith(
                    repository.getComicsByHeroId(characterId)
                            .asLiveData()
            ) { character, responseComic ->
                character?.comics?.items = responseComic?.data?.comics
                character
            }

    val imageUrl = character
            .map { it?.thumbnail }

    val description = character
            .map { it?.description }

    val comics = character
            .map { it?.comics?.items }


    class Factory(private val repository: MarvelRepository) : ViewModelProvider.Factory {
        private var characterId: Int = -1

        fun setCharacterId(id: Int) {
            characterId = id
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CharacterDetailViewModel(repository, characterId) as T
        }
    }
}