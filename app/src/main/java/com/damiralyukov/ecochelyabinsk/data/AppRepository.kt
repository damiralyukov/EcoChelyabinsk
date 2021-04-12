package com.damiralyukov.ecochelyabinsk.data

import javax.inject.Inject

class AppRepository @Inject constructor(
        private val appDao: AppDao
) {
    fun getCompaniesByCategory(category: Category, district: District) = appDao.getCompaniesByCategory(category, district)

    suspend fun insertCompany(company: Company) = appDao.insertCompany(company)

    suspend fun deleteCompany(company: Company) = appDao.deleteCompany(company)

    suspend fun updateCompany(company: Company) = appDao.updateCompany(company)
}