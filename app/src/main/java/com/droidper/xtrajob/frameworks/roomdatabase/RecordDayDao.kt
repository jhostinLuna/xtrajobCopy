package com.droidper.xtrajob.frameworks.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droidper.xtrajob.data.model.RecordDayEntity

@Dao
interface RecordDayDao {
    @Query("SELECT * FROM record_day")
    fun getAll(): List<RecordDayEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRecordDay(record: RecordDayEntity): Long
}