package com.droidper.xtrajob.ui.timepicker

data class TimePickerUiState (
    val timerPickerModel: TimeUiModel = TimeUiModel(),
    val isLoading: Boolean = false
)