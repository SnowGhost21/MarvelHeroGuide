package com.diegocunha.marvelheroguide.view.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diegocunha.marvelheroguide.model.data.Character
import com.diegocunha.marvelheroguide.model.repository.MarvelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class CharacterDetailViewModel(private val repository: MarvelRepository,
                               private val characterId: Int) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character


    suspend fun getHeroById() = withContext(Dispatchers.IO) {
        _isLoading.postValue(true)
        try {
            val heroRequest = async {
                repository.getHeroById(characterId)
            }

            val comicsHeroRequest = async {
                repository.getComicsByHeroId(characterId)
            }

            val heroResponse = heroRequest.await()
            val comicHeroResponse = comicsHeroRequest.await()

            if (heroResponse.isSuccessful && comicHeroResponse.isSuccessful) {
                val responseHero = heroResponse.body()?.data?.characters?.first()
                val responseComic = comicHeroResponse.body()?.data?.comics


                responseHero?.let {

                    val comicsItems = responseComic?.toTypedArray()
                    val filteredItems = comicsItems?.filter { comicSummary ->  !comicSummary.description.isNullOrEmpty() }

                    if (filteredItems != null) {
                        it.comics?.items?.clear()
                        it.comics?.items?.addAll(filteredItems)
                    }

                    _character.postValue(it)
                }
            }

        } catch (e: Exception) {

        } finally {
            _isLoading.postValue(false)
        }


    }


//    val character = repository.getHeroById(characterId)
//            .map { it.data.characters.first() }
//            .doOnSubscribe { _isLoading.postValue(true) }
//            .asLiveData()
//            .combineWith(
//                    repository.getComicsByHeroId(characterId)
//                            .asLiveData()
//            ) { character, responseComic ->
//                character?.comics?.items = responseComic?.data?.comics
//                character
//            }

    val imageUrl = ""

    val description = ""

    val comics = ""


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