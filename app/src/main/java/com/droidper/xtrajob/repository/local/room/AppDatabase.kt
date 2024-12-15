package com.droidper.xtrajob.repository.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.droidper.xtrajob.model.RecordDay

@Database(entities = [RecordDay::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun recordDayDao ():RecordDayDao
}