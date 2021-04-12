package com.damiralyukov.ecochelyabinsk.ui.fragments

import android.app.AlertDialog
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
import com.damiralyukov.ecochelyabinsk.databinding.FragmentUpdateCompanyBinding
import com.damiralyukov.ecochelyabinsk.ui.fragments.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateCompanyFragment : Fragment() {
    private val mainViewModel: MainViewModel by viewModels()
    private val args: UpdateCompanyFragmentArgs by navArgs()

    private var _binding: FragmentUpdateCompanyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateCompanyBinding.inflate(inflater, container, false)

        binding.args = args

        binding.btnSave.setOnClickListener {
            onSaveButtonClick()
        }
        binding.btnDelete.setOnClickListener {
            onDeleteButtonClick()
        }

        return binding.root
    }

    private fun onDeleteButtonClick() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Да") { _, _ ->
            mainViewModel.deleteCompany(args.company)

            Toast.makeText(requireContext(), "Успешно удалено!", Toast.LENGTH_LONG).show()

            findNavController().navigate(UpdateCompanyFragmentDirections.actionUpdateCompanyFragmentToCategoryFragment(args.company.category))
        }
        builder.setNegativeButton("Нет") { _, _ -> }
        builder.setTitle("Удалить ${args.company.title}?")
        builder.setMessage("Вы действительно хотите удалить ${args.company.title}?")
        builder.create().show()
    }

    private fun onSaveButtonClick() {
        val title = binding.title.text.toString()
        val address = binding.address.text.toString()
        val phone = binding.phone.text.toString()
        val district = mainViewModel.parseDistrictFromString(binding.district.selectedItem.toString())
        val category = args.company.category

        val validation = mainViewModel.validate(title, address, phone)
        if (validation) {
            val company = Company(
                    args.company.id,
                    title,
                    address,
                    phone,
                    district,
                    category
            )

            mainViewModel.updateCompany(company)

            Toast.makeText(requireContext(), "Успешно обновлено!", Toast.LENGTH_LONG).show()

            findNavController().navigate(UpdateCompanyFragmentDirections.actionUpdateCompanyFragmentToCategoryFragment(category))
        } else {
            Toast.makeText(requireContext(), "Пожалуйста, заполните все поля!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}