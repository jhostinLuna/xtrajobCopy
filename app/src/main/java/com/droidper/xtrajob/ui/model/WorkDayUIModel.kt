package com.droidper.xtrajob.ui.model

import java.time.LocalDateTime

data class WorkDayUIModel(
    val startDayWorkTime: LocalDateTime = LocalDateTime.now().withHour(0).withMinute(0),
    val endDayWorkTime: LocalDateTime = LocalDateTime.now().withHour(0).withMinute(0),
    val startDayBreakTime: LocalDateTime = LocalDateTime.now().withHour(0).withMinute(0),
    val endDayBreakTime: LocalDateTime = LocalDateTime.now().withHour(0).withMinute(0),
    val isBreak: Boolean = false,
    val observation: String = ""
)
