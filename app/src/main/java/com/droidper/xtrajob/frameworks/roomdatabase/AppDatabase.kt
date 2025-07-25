package com.droidper.xtrajob.frameworks.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.droidper.xtrajob.data.model.RecordDayEntity

@Database(entities = [RecordDayEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun recordDayDao (): RecordDayDao
}