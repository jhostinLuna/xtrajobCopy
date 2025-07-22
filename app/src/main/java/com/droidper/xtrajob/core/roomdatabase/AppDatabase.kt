package com.droidper.xtrajob.core.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RecordDay::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun recordDayDao (): RecordDayDao
}