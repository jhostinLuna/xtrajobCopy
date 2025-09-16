package com.droidper.xtrajob.ui.model

data class SaveStatusUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
)
