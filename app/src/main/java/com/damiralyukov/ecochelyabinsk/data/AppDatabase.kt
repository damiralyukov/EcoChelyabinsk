package com.damiralyukov.ecochelyabinsk.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
        entities = [Company::class],
        version = 1,
        exportSchema = false
)
@TypeConverters(CompanyConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAppDao(): AppDao
}