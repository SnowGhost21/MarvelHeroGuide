package com.diegocunha.marvelheroguide.view.home

import android.content.Context
import com.diegocunha.marvelheroguide.R
import com.diegocunha.marvelheroguide.model.data.Character

class CharacterListItemViewModel(private val character: Character,
                                 private val context: Context) {

    val name = character.name

    val description = if (!character.description.isNullOrEmpty()) character.description else context.getString(R.string.description_not_available)

    val imageUrl = character.thumbnail?.path + "." + character.thumbnail?.extension
}