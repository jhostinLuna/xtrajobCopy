package com.droidper.xtrajob.core.di

import android.content.Context
import androidx.room.Room
import com.droidper.xtrajob.repository.local.room.AppDatabase
import com.droidper.xtrajob.repository.local.room.RecordDayDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext:Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "AppDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRecordDayDao(appDatabase: AppDatabase): RecordDayDao {
        return appDatabase.recordDayDao()
    }
}