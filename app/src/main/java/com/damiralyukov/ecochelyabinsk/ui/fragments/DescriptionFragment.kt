package com.damiralyukov.ecochelyabinsk.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.damiralyukov.ecochelyabinsk.R
import com.damiralyukov.ecochelyabinsk.data.Category

class DescriptionFragment(
        private val category: Category
) : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutByCategory(), container, false)
    }

    private fun getLayoutByCategory() = when (category) {
        Category.WASTEPAPER -> {
            R.layout.fragment_wastepaper_description
        }
        Category.PLASTIC -> {
            R.layout.fragment_plastic_description
        }
        Category.BATTERIES -> {
            R.layout.fragment_batteries_description
        }
    }
}