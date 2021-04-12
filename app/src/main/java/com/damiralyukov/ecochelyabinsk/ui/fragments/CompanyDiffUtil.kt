package com.damiralyukov.ecochelyabinsk.ui.fragments

import androidx.recyclerview.widget.DiffUtil
import com.damiralyukov.ecochelyabinsk.data.Company

class CompanyDiffUtil(
        private val oldList: List<Company>,
        private val newList: List<Company>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id &&
                oldList[oldItemPosition].title == newList[newItemPosition].title &&
                oldList[oldItemPosition].address == newList[newItemPosition].address &&
                oldList[oldItemPosition].phone == newList[newItemPosition].phone &&
                oldList[oldItemPosition].district == newList[newItemPosition].district &&
                oldList[oldItemPosition].category == newList[newItemPosition].category
    }

}