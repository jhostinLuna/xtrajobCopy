package com.droidper.xtrajob.data.di

import android.content.Context
import androidx.room.Room
import com.droidper.xtrajob.frameworks.roomdatabase.AppDatabase
import com.droidper.xtrajob.frameworks.roomdatabase.RecordDayDao
import com.droidper.xtrajob.domain.RecordDayRepository
import com.droidper.xtrajob.data.RecordDayRepositoryImpl
import dagger.Binds
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
            "Xtrajob_DB"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRecordDayDao(appDatabase: AppDatabase): RecordDayDao {
        return appDatabase.recordDayDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class BindRepositoryModule{
    @Binds
    abstract fun getRecordDayRepository(recordDayRepositoryImpl: RecordDayRepositoryImpl): RecordDayRepository
}