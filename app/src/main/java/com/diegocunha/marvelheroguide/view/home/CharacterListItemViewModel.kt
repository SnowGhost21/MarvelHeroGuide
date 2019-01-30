package com.diegocunha.marvelheroguide.view.home

import com.diegocunha.marvelheroguide.model.data.Character

class CharacterListItemViewModel(private val character: Character) {

    val name = character.name

    val description = character.description

    val imageUrl = character.thumbnail?.path + "." + character.thumbnail?.extension
}