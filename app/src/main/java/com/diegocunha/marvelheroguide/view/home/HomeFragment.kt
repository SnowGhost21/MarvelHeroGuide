package com.diegocunha.marvelheroguide.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.diegocunha.marvelheroguide.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        val adapter = CharactersAdapter()
        binding.heroesRecyclerView.adapter = adapter
        binding.heroesRecyclerView.addItemDecoration(ListItemDecoration())

//        viewModel.requestHeroes(20, 0)


        viewModel.heroes.observe(this, Observer {
           it?.let { characters -> adapter.setItems(characters) }
        })



        return binding.root
    }
}