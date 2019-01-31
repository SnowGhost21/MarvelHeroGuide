package com.diegocunha.marvelheroguide.view.character

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
import com.diegocunha.marvelheroguide.databinding.FragmentCharacterDetailBinding
import com.diegocunha.marvelheroguide.view.MainActivity
import com.diegocunha.marvelheroguide.view.comic.ComicsAdapter
import org.koin.android.ext.android.inject

class CharacterDetailFragment : Fragment() {

    private val factory: CharacterDetailViewModel.Factory by inject()

    private val viewModel: CharacterDetailViewModel by lazy {
        ViewModelProviders.of(this, factory)
                .get(CharacterDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = TransitionInflater.from(context)
                .inflateTransition(R.transition.default_transition)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        val characterId = CharacterDetailFragmentArgs.fromBundle(arguments).characterId
        val characterName = CharacterDetailFragmentArgs.fromBundle(arguments).characterName


        (activity as MainActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = characterName

        }
        factory.setCharacterId(characterId)

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        viewModel.description.observe(this, Observer {
            binding.description.text = if (!it.isNullOrEmpty()) it else getString(R.string.description_not_available)
        })

        ViewCompat.setTransitionName(binding.coordinator, characterId.toString())
        ViewCompat.setTransitionName(binding.characterImage, "image_$characterId")

        val adapter = ComicsAdapter()
        binding.comicsRecyclerView.adapter = adapter

        viewModel.comics.observe(this, Observer {
            it?.let { comics ->
                adapter.setItems(comics)
                binding.progressBar.visibility = View.GONE
            }
        })



        return binding.root
    }
}