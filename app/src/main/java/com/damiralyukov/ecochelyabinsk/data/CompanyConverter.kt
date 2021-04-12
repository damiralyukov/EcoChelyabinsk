package com.damiralyukov.ecochelyabinsk.data

import androidx.room.TypeConverter

class CompanyConverter {
    @TypeConverter
    fun fromDistrict(district: District): String = district.toString()

    @TypeConverter
    fun toDistrict(district: String): District {
        return District.valueOf(district)
    }

    @TypeConverter
    fun fromCategory(category: Category): String = category.toString()

    @TypeConverter
    fun toCategory(category: String): Category {
        return Category.valueOf(category)
    }
}