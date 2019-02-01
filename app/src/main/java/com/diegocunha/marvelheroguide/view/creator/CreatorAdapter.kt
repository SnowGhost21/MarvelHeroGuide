package com.diegocunha.marvelheroguide.view.creator

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.diegocunha.marvelheroguide.databinding.CreatorItemBinding
import com.diegocunha.marvelheroguide.model.data.CreatorSummary
import com.diegocunha.marvelheroguide.view.databinding.ReactiveAdapter

class CreatorAdapter: ReactiveAdapter<CreatorSummary, CreatorItemBinding>() {

    override fun getBinding(context: Context, parent: ViewGroup, viewType: Int): CreatorItemBinding {
        return CreatorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun bind(binding: CreatorItemBinding, item: CreatorSummary) {
       val viewModel = CreatorItemViewModel(item)
        binding.viewModel = viewModel
    }
}