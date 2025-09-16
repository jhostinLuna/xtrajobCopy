package com.droidper.xtrajob.ui.model

data class TimeBreakWorkUiState (
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val timeBreakWork: Int = 0,
    val isHours: Boolean = false
)
