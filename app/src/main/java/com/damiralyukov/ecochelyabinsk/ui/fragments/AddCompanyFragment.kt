package com.damiralyukov.ecochelyabinsk.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.damiralyukov.ecochelyabinsk.data.Company
import com.damiralyukov.ecochelyabinsk.databinding.FragmentAddCompanyBinding
import com.damiralyukov.ecochelyabinsk.ui.fragments.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCompanyFragment : Fragment() {
    private val mainViewModel: MainViewModel by viewModels()
    private val args: AddCompanyFragmentArgs by navArgs()

    private var _binding: FragmentAddCompanyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCompanyBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.btnSave.setOnClickListener {
            onSaveButtonClick()
        }

        return binding.root
    }

    private fun onSaveButtonClick() {
        val title = binding.title.text.toString()
        val address = binding.address.text.toString()
        val phone = binding.phone.text.toString()
        val district = mainViewModel.parseDistrictFromString(binding.district.selectedItem.toString())
        val category = args.category

        val validation = mainViewModel.validate(title, address, phone)
        if (validation) {
            val company = Company(
                    0,
                    title,
                    address,
                    phone,
                    district,
                    category
            )

            mainViewModel.insertCompany(company)

            Toast.makeText(requireContext(), "Успешно добавлено!", Toast.LENGTH_LONG).show()

            findNavController().navigate(AddCompanyFragmentDirections.actionAddCompanyFragmentToCategoryFragment(category))
        } else {
            Toast.makeText(requireContext(), "Пожалуйста, заполните все поля!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}