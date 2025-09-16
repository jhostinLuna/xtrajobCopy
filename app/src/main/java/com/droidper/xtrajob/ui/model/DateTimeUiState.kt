package com.droidper.xtrajob.ui.model

import java.time.LocalDateTime

data class DateTimeUiState(
    val dateTime: LocalDateTime = LocalDateTime.now().withHour(0).withMinute(0),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)