package com.damiralyukov.ecochelyabinsk.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.damiralyukov.ecochelyabinsk.data.Category
import com.damiralyukov.ecochelyabinsk.databinding.FragmentAddressesBinding
import com.damiralyukov.ecochelyabinsk.ui.fragments.viewmodels.MainViewModel
import com.damiralyukov.ecochelyabinsk.utils.observeOnce
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

@AndroidEntryPoint
class AddressesFragment(
        private val category: Category
) : Fragment() {
    private val mainViewModel: MainViewModel by viewModels()
    private val adapter: CompanyAdapter by lazy { CompanyAdapter() }

    private var _binding: FragmentAddressesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddressesBinding.inflate(inflater, container, false)

        binding.mainViewModel = mainViewModel
        binding.category = category
        binding.lifecycleOwner = this

        setupSpinner()
        setupRecyclerView()

        binding.btnAddCompany.setOnClickListener {
            findNavController().navigate(CategoryFragmentDirections.actionCategoryFragmentToAddCompanyFragment(category))
        }

        return binding.root
    }

    private fun setupSpinner() {
        val spinner = binding.district

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val district = mainViewModel.parseDistrictFromString(spinner.selectedItem.toString())

                mainViewModel.getCompaniesByCategory(category, district).observeOnce(viewLifecycleOwner, { companyList ->
                    mainViewModel.isCategoryEmpty(companyList)
                    adapter.setList(companyList)
                })
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {  }
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerView

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.itemAnimator = SlideInUpAnimator().apply { addDuration = 250 }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}