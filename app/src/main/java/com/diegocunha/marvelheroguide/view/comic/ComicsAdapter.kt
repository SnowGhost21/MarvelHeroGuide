package com.diegocunha.marvelheroguide.view.comic

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.diegocunha.marvelheroguide.databinding.ComicItemBinding
import com.diegocunha.marvelheroguide.model.data.ComicSummary
import com.diegocunha.marvelheroguide.view.databinding.ReactiveAdapter

class ComicsAdapter: ReactiveAdapter<ComicSummary, ComicItemBinding>() {


    override fun getBinding(context: Context, parent: ViewGroup, viewType: Int): ComicItemBinding {
        return ComicItemBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun bind(binding: ComicItemBinding, item: ComicSummary) {
        val viewModel = ComicItemViewModel(item)
        binding.viewModel = viewModel
    }
}