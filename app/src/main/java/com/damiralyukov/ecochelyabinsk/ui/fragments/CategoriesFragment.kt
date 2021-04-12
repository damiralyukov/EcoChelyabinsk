package com.damiralyukov.ecochelyabinsk.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.damiralyukov.ecochelyabinsk.data.Category
import com.damiralyukov.ecochelyabinsk.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)

        // Wastepaper category button
        binding.btnWastepaper.setOnClickListener {
            findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragmentToCategoryFragment(Category.WASTEPAPER))
        }
        // Plastic category button
        binding.btnPlastic.setOnClickListener {
            findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragmentToCategoryFragment(Category.PLASTIC))
        }
        // Batteries category button
        binding.btnBatteries.setOnClickListener {
            findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragmentToCategoryFragment(Category.BATTERIES))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}