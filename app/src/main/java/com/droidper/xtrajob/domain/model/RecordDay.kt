package com.droidper.xtrajob.domain.model

import java.time.LocalDateTime

data class RecordDay(
    val startDay: LocalDateTime,
    val endDay:LocalDateTime,
    val startBreakWork: LocalDateTime,
    val endBreakWork: LocalDateTime,
    val observations: String
)
