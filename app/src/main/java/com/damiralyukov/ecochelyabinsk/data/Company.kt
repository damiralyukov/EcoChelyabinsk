package com.damiralyukov.ecochelyabinsk.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "companies_table")
@Parcelize
data class Company(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val title: String,
        val address: String,
        val phone: String,
        val district: District,
        val category: Category
) : Parcelable