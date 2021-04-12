package com.damiralyukov.ecochelyabinsk.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AppDao {
    @Query("SELECT * FROM companies_table WHERE category = :category AND district = :district")
    fun getCompaniesByCategory(category: Category, district: District): LiveData<List<Company>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCompany(company: Company)

    @Delete
    suspend fun deleteCompany(company: Company)

    @Update
    suspend fun updateCompany(company: Company)
}