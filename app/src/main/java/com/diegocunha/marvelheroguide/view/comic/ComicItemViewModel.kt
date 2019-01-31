package com.diegocunha.marvelheroguide.view.comic

import com.diegocunha.marvelheroguide.model.data.ComicSummary

class ComicItemViewModel(private val comic: ComicSummary) {

    val title = comic.title

    val description = comic.description

    val image = comic.thumbnail
}