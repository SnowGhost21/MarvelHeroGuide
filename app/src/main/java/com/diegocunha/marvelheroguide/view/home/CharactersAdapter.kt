package com.diegocunha.marvelheroguide.view.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.diegocunha.marvelheroguide.databinding.CharacterItemBinding
import com.diegocunha.marvelheroguide.model.data.Character
import com.diegocunha.marvelheroguide.view.databinding.ReactiveAdapter

class CharactersAdapter : ReactiveAdapter<Character, CharacterItemBinding>() {

    override fun getBinding(context: Context, parent: ViewGroup, viewType: Int): CharacterItemBinding {
        return CharacterItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
    }

    override fun bind(binding: CharacterItemBinding, item: Character) {
        val viewModel = CharacterListItemViewModel(item)
        binding.viewModel = viewModel

    }
}