package com.droidper.xtrajob.ui.view.newworkday

import com.droidper.xtrajob.ui.model.WorkDayUIModel

data class WorkDayUiState(
    val dayWorkDayUIModel: WorkDayUIModel = WorkDayUIModel(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
