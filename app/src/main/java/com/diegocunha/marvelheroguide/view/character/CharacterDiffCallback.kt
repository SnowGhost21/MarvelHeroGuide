package com.diegocunha.marvelheroguide.view.character

import androidx.recyclerview.widget.DiffUtil
import com.diegocunha.marvelheroguide.model.data.Character

class CharacterDiffCallback(
        private val oldList: List<Character>,
        private val newList: List<Character>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}

data class CharacterNavigationParams(val characterId: String)