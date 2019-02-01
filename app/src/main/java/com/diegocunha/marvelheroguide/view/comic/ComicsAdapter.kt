package com.diegocunha.marvelheroguide.view.comic

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.diegocunha.marvelheroguide.databinding.ComicItemBinding
import com.diegocunha.marvelheroguide.model.data.ComicSummary
import com.diegocunha.marvelheroguide.view.databinding.ReactiveAdapter

class ComicsAdapter : ReactiveAdapter<ComicSummary, ComicItemBinding>() {

    private val _comicDetail = MutableLiveData<ComicNavigationParams>()
    val comicDetail: LiveData<ComicNavigationParams> = _comicDetail

    override fun getBinding(context: Context, parent: ViewGroup, viewType: Int): ComicItemBinding {
        return ComicItemBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun bind(binding: ComicItemBinding, item: ComicSummary) {
        val viewModel = ComicItemViewModel(item)
        binding.viewModel = viewModel

        ViewCompat.setTransitionName(binding.card, item.id.toString())
        ViewCompat.setTransitionName(binding.comicImage, "image_${item.id}")

        setNavigationClickListener(binding, item)
    }

    private fun setNavigationClickListener(binding: ComicItemBinding, item: ComicSummary) {
        val sharedViews = listOf(binding.card,
                binding.comicImage)

        binding.card.setOnClickListener {
            _comicDetail.postValue(ComicNavigationParams(item.id, item.title, sharedViews))
        }
    }


}

data class ComicNavigationParams(val comicId: Int, val comicTitle: String, val sharedViews: List<View>)