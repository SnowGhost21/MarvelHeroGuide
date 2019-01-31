package com.diegocunha.marvelheroguide.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegocunha.marvelheroguide.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel


        val adapter = CharactersAdapter()
        binding.heroesRecyclerView.adapter = adapter
        binding.heroesRecyclerView.addItemDecoration(ListItemDecoration())
        viewModel.loadHeroes()
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
                        viewModel.loadHeroes()
                    }
                }
            }

        })


        return binding.root
    }
}