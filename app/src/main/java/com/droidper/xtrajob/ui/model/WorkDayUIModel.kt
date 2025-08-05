package com.droidper.xtrajob.ui.model

import com.droidper.xtrajob.ui.timepicker.TimeUiModel

data class WorkDayUIModel(
    val dateStartWorkday: String = "",
    val dateEndWorkday: String = "",
    val startDayWorkTimeUiModel: TimeUiModel = TimeUiModel(),
    val endDayWorkTimeUiModel: TimeUiModel = TimeUiModel(),
    val startDayBreakTimeUiModel: TimeUiModel = TimeUiModel(),
    val endDayBreakTimeUiModel: TimeUiModel = TimeUiModel(),
    val isBreak: Boolean = false,
    val observation: String = ""
)
