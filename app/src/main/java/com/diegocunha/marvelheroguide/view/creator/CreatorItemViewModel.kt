package com.diegocunha.marvelheroguide.view.creator

import com.diegocunha.marvelheroguide.model.data.CreatorSummary

class CreatorItemViewModel(private val item: CreatorSummary) {

    val name = item.fullName

    val image = item.thumbnail
}