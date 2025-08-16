package com.droidper.xtrajob.ui.timepicker

import java.time.LocalDateTime

class TimeUiModel(
    val dateTime: LocalDateTime = LocalDateTime.now().withHour(0).withMinute(0),
    val is24HourView: Boolean = true,
)
