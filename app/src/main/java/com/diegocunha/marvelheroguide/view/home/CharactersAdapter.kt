package com.diegocunha.marvelheroguide.view.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.diegocunha.marvelheroguide.databinding.CharacterItemBinding
import com.diegocunha.marvelheroguide.model.data.Character
import com.diegocunha.marvelheroguide.view.databinding.ReactiveAdapter

class CharactersAdapter : ReactiveAdapter<Character, CharacterItemBinding>() {

    private val _detailHero = MutableLiveData<CharacterNavigationParams>()
    val detailHero: LiveData<CharacterNavigationParams> = _detailHero

    private lateinit var context: Context

    override fun getBinding(context: Context, parent: ViewGroup, viewType: Int): CharacterItemBinding {
        this.context = context
        return CharacterItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
    }

    override fun bind(binding: CharacterItemBinding, item: Character) {
        val viewModel = CharacterListItemViewModel(item, context)
        binding.viewModel = viewModel

        ViewCompat.setTransitionName(binding.card, item.id.toString())
        ViewCompat.setTransitionName(binding.imageView, "image_${item.id}")

        setNavigationClickListener(binding, item)

    }

    private fun setNavigationClickListener(binding: CharacterItemBinding, item: Character) {
        val sharedViews = listOf(binding.card,
                binding.imageView)

        binding.card.setOnClickListener {
            _detailHero.postValue(CharacterNavigationParams(item.id, item.name, sharedViews))
        }
    }
}

data class CharacterNavigationParams(val id: Int, val name: String, val sharedViews: List<View>)