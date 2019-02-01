package com.diegocunha.marvelheroguide.view.comic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionInflater
import com.diegocunha.marvelheroguide.R
import com.diegocunha.marvelheroguide.databinding.FragmentComicDetailBinding
import com.diegocunha.marvelheroguide.view.MainActivity
import com.diegocunha.marvelheroguide.view.creator.CreatorAdapter
import org.koin.android.ext.android.inject

class ComicDetailFragment : Fragment() {


    private val factory: ComicDetailViewModel.Factory by inject()
    private val viewModel: ComicDetailViewModel by lazy {
        ViewModelProviders.of(this, factory)
                .get(ComicDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = TransitionInflater.from(context)
                .inflateTransition(R.transition.default_transition)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentComicDetailBinding.inflate(inflater, container, false)
        val comicId = ComicDetailFragmentArgs.fromBundle(arguments).comicId
        val title = ComicDetailFragmentArgs.fromBundle(arguments).comicTitle
        factory.setComicId(comicId)

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        (activity as MainActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            this.title = title
        }

        viewModel.description.observe(this, Observer {
            binding.description.text = if (!it.isNullOrEmpty()) it else getString(R.string.description_not_available)
        })

        ViewCompat.setTransitionName(binding.coordinator, comicId.toString())
        ViewCompat.setTransitionName(binding.comicImage, "image_$comicId")

        val adapter = CreatorAdapter()
        binding.creatorRecyclerView.adapter = adapter

        viewModel.creators.observe(this, Observer {
            it?.let { creators -> adapter.setItems(creators) }
        })

        return binding.root
    }
}