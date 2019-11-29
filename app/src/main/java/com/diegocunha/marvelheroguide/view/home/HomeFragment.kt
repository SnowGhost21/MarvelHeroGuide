package com.diegocunha.marvelheroguide.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegocunha.marvelheroguide.R
import com.diegocunha.marvelheroguide.databinding.FragmentHomeBinding
import com.diegocunha.marvelheroguide.view.MainActivity
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    init {
        lifecycleScope.launchWhenCreated {
            viewModel.loadHeroes()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        (activity as MainActivity).supportActionBar?.apply {
            title = getString(R.string.home_title)
            setDisplayHomeAsUpEnabled(false)
        }


        val adapter = CharactersAdapter()
        adapter.detailHero.observe(this, Observer {
            openCharacterDetailFragment(it)
        })


        binding.heroesRecyclerView.adapter = adapter
        binding.heroesRecyclerView.addItemDecoration(ListItemDecoration())


        viewModel.heroes.observe(this, Observer {
            it?.let { characters -> adapter.setItems(characters) }
        })

        viewModel.error.observe(this, Observer {
            it?.let {
                Log.e("Error", "Error request")
            }
        })


        binding.heroesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val manager = recyclerView.layoutManager as? LinearLayoutManager
                manager?.let {
                    val visibleItemCount = it.childCount
                    val totalItemCount = it.itemCount

                    val firstVisibleItemPosition = it.findFirstVisibleItemPosition()

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                        lifecycleScope.launch {
                            viewModel.loadHeroes()
                        }
                    }
                }
            }

        })


        return binding.root
    }


    private fun openCharacterDetailFragment(params: CharacterNavigationParams) {
        val characterId = params.id
        val characterName = params.name
        val action = HomeFragmentDirections.actionHomeFragmentToCharacterDetailFragment(characterId, characterName)


        val extras = FragmentNavigator.Extras.Builder().apply {
            params.sharedViews.forEach {
                addSharedElement(it, ViewCompat.getTransitionName(it) ?: "")
            }
        }.build()

        findNavController().navigate(action, extras)
    }
}