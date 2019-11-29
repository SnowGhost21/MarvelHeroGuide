package com.diegocunha.marvelheroguide.view.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.diegocunha.marvelheroguide.R
import com.diegocunha.marvelheroguide.databinding.FragmentCharacterDetailBinding
import com.diegocunha.marvelheroguide.view.MainActivity
import com.diegocunha.marvelheroguide.view.comic.ComicsAdapter
import com.diegocunha.marvelheroguide.view.databinding.loadImageUri
import org.koin.android.ext.android.inject

class CharacterDetailFragment : Fragment() {

    private val factory: CharacterDetailViewModel.Factory by inject()

    private val viewModel: CharacterDetailViewModel by lazy {
        ViewModelProviders.of(this, factory)
                .get(CharacterDetailViewModel::class.java)
    }

    init {
        lifecycleScope.launchWhenCreated {
            viewModel.getHeroById()
        }
    }

    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = TransitionInflater.from(context)
                .inflateTransition(R.transition.default_transition)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        val characterId = args.characterId
        val characterName = args.characterName


        (activity as MainActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = characterName

        }
        factory.setCharacterId(characterId)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = ComicsAdapter()
        adapter.comicDetail.observe(this, Observer {
            //            openComicDetails(it)
        })

        viewModel.character.observe(this, Observer { character ->
            binding.description.text = if (!character.description.isNullOrEmpty()) character.description else getString(R.string.description_not_available)

            character.comics?.let { comic ->
                comic.items?.let { comicItems ->
                    adapter.setItems(comicItems)
                }
            }

            character.thumbnail?.let { image ->
                loadImageUri(binding.characterImage, image)
            }
        })


        ViewCompat.setTransitionName(binding.coordinator, characterId.toString())
        ViewCompat.setTransitionName(binding.characterImage, "image_$characterId")

        binding.comicsRecyclerView.adapter = adapter

//        viewModel.comics.observe(this, Observer {
//            it?.let { comics ->
//                adapter.setItems(comics)
//                binding.progressBar.visibility = View.GONE
//            }
//        })

        return binding.root
    }

//    private fun openComicDetails(parameters: ComicNavigationParams) {
//        val id = parameters.comicId
//        val title = parameters.comicTitle
//
//        val action = CharacterDetailFragmentDirections.actionCharacterDetailFragmentToComicDetailFragment(id, title)
//        val extras = FragmentNavigator.Extras.Builder().apply {
//            parameters.sharedViews.forEach {
//                addSharedElement(it, ViewCompat.getTransitionName(it) ?: "")
//            }
//        }.build()
//
//        findNavController().navigate(action, extras)
//    }
}