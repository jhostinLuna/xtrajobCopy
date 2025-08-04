package com.droidper.xtrajob.ui.timepicker

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class TimeUiModel(
    val hour: String = "00",
    val minute: String = "00",
    val instantOfDate: Instant = Instant.now(),
    val is24HourView: Boolean = true,
) {
    fun toLocalDateTime(): LocalDateTime {
        val dateTime = LocalDateTime.ofInstant(instantOfDate, ZoneId.systemDefault())
        dateTime.withHour(hour.toInt())
        dateTime.withMinute(minute.toInt())
        return dateTime
    }
}
