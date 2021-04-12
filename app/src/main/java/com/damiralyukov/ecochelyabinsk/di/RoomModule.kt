package com.damiralyukov.ecochelyabinsk.di

import android.content.Context
import androidx.room.Room
import com.damiralyukov.ecochelyabinsk.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
            @ApplicationContext context: Context
    ) = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
    ).createFromAsset("db/app_database").build()

    @Provides
    @Singleton
    fun provideAppDao(appDatabase: AppDatabase) = appDatabase.getAppDao()
}