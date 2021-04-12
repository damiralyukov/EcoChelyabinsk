package com.damiralyukov.ecochelyabinsk.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.damiralyukov.ecochelyabinsk.databinding.FragmentCategoryBinding


class CategoryFragment : Fragment() {
    private val args: CategoryFragmentArgs by navArgs()

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)

        val category = args.category
        val fragments = arrayListOf(
                AddressesFragment(category),
                DescriptionFragment(category)
        )
        val adapter = CategoryAdapter(
                fragments,
                requireActivity().supportFragmentManager,
                lifecycle
        )

        binding.viewpager.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}