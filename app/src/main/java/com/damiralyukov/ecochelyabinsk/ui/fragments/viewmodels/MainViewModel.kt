package com.damiralyukov.ecochelyabinsk.ui.fragments.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damiralyukov.ecochelyabinsk.data.AppRepository
import com.damiralyukov.ecochelyabinsk.data.Category
import com.damiralyukov.ecochelyabinsk.data.Company
import com.damiralyukov.ecochelyabinsk.data.District
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        private val appRepository: AppRepository
) : ViewModel() {
    val isEmpty: MutableLiveData<Boolean> = MutableLiveData(false)

    fun isCategoryEmpty(companyList: List<Company>) {
        isEmpty.value = companyList.isEmpty()
    }

    fun getCompaniesByCategory(category: Category, district: District) = appRepository.getCompaniesByCategory(category, district)

    fun validate(title: String, address: String, phone: String): Boolean {
        return !(title.isEmpty() || address.isEmpty() || phone.isEmpty())
    }

    fun insertCompany(company: Company) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.insertCompany(company)
        }
    }

    fun deleteCompany(company: Company) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.deleteCompany(company)
        }
    }

    fun updateCompany(company: Company) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.updateCompany(company)
        }
    }

    fun parseDistrictFromString(district: String): District {
        return when (district) {
            "Центральный" -> District.CENTRALNIY
            "Тополиная аллея" -> District.TOPOLINAYA_ALLEYA
            "Северо-запад" -> District.SEVERO_ZAPAD
            "Советский" -> District.SOVETSKIY
            "ЧТЗ" -> District.CHTZ
            "ЧМЗ" -> District.CHMZ
            "Ленинский" -> District.LENINSKIY
            "Курчатовский" -> District.KURCHATOVSKIY
            else -> {
                District.CENTRALNIY
            }
        }
    }

    fun parseCategory(category: Category): String {
        return when (category) {
            Category.WASTEPAPER -> "Макулатура"
            Category.PLASTIC -> "Пластик"
            Category.BATTERIES -> "Батарейки"
        }
    }
}