package com.droidper.xtrajob.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "record_day")
data class RecordDayEntity (
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "start_day") val startDay: Long,
    @ColumnInfo(name = "end_day") val endDay:Long,
    @ColumnInfo(name = "start_break_work") val startBreakWork: Long,
    @ColumnInfo(name = "end_break_work") val endBreakWork: Long,
    @ColumnInfo(name = "observations") val observations: String
        )