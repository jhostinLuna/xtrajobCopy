package com.droidper.xtrajob.core.roomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecordDayDao {
    @Query("SELECT * FROM record_day")
    fun getAll(): List<RecordDay>

    @Insert
    fun saveRecordDay(record: RecordDay)

    @Insert
    fun insertAll(vararg record: RecordDay)

    @Delete
    fun delete(record: RecordDay)
}