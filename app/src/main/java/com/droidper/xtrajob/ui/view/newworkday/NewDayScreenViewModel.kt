package com.droidper.xtrajob.ui.view.newworkday

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidper.xtrajob.core.common.Resource
import com.droidper.xtrajob.core.extensions.timeMillisToLocalDateTime
import com.droidper.xtrajob.domain.usecase.SaveWorkDayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewDayScreenViewModel @Inject constructor(
    private val saveWorkDayUseCase: SaveWorkDayUseCase,
    private val workDayUiModelToDomainMapper: WorkDayUiModelToDomainMapper
) : ViewModel() {
    private val tag = this@NewDayScreenViewModel.javaClass.name
    private val _workDayUiState: MutableStateFlow<WorkDayUiState> = MutableStateFlow(WorkDayUiState())
    val workDayUiState: StateFlow<WorkDayUiState> = _workDayUiState.asStateFlow()

    fun saveWorkDay() {
        viewModelScope.launch {
            saveWorkDayUseCase(
                SaveWorkDayUseCase.Params(workDayUiModelToDomainMapper.map(_workDayUiState.value.dayWorkDayUIModel))
            ){resource->
                when(resource) {
                    is Resource.Error -> {
                        Log.d(tag, "saveWorkDay: Error")
                    }
                    is Resource.Success -> {
                        Log.d(tag, "saveWorkDay: Success")
                    }
                    else -> {
                        Log.d(tag, "saveWorkDay: Loading")
                    }
                }
            }
        }

    }
    fun updateTimeWorkStart(hour: Int, minute: Int) {
        _workDayUiState.update {
            WorkDayUiState(
                dayWorkDayUIModel = it.dayWorkDayUIModel.copy(
                    startDayWorkTime = it.dayWorkDayUIModel.startDayWorkTime.withHour(hour)
                        .withMinute(minute)
                ),
                isLoading = false,
                isError = false,
                errorMessage = ""
            )
        }
    }
    fun updateTimeWorkEnd(hour: Int, minute: Int) {
        _workDayUiState.update {
            WorkDayUiState(
                dayWorkDayUIModel = it.dayWorkDayUIModel.copy(
                    endDayWorkTime = it.dayWorkDayUIModel.endDayWorkTime.withHour(hour)
                        .withMinute(minute)
                ),
                isLoading = false,
                isError = false,
                errorMessage = ""
            )
        }
    }

    fun changeBreakWorkState() {
        _workDayUiState.update {
            WorkDayUiState(
                dayWorkDayUIModel = it.dayWorkDayUIModel.copy(isBreak = !it.dayWorkDayUIModel.isBreak),
                isLoading = false,
                isError = false,
                errorMessage = ""
            )
        }
    }

    /**
     * Actualiza la fecha de inicio de la jornada laboral
     */
    fun updateDateStartWorkDay(dateMillis: Long) {
        _workDayUiState.update {
            WorkDayUiState(
                dayWorkDayUIModel = it.dayWorkDayUIModel.copy(
                    startDayWorkTime = dateMillis.timeMillisToLocalDateTime()
                        .withHour(it.dayWorkDayUIModel.startDayWorkTime.hour)
                        .withMinute(it.dayWorkDayUIModel.startDayWorkTime.minute)
                ),
                isLoading = false,
                isError = false,
                errorMessage = ""
            )
        }
    }

    /**
     * Actualiza la fecha de fin de la jornada laboral
     */
    fun updateDateEndWorkDay(dateMillis: Long) {
        _workDayUiState.update {
            WorkDayUiState(
                dayWorkDayUIModel = it.dayWorkDayUIModel.copy(
                    endDayWorkTime = dateMillis.timeMillisToLocalDateTime()
                        .withHour(it.dayWorkDayUIModel.endDayWorkTime.hour)
                        .withMinute(it.dayWorkDayUIModel.endDayWorkTime.minute)
                ),
                isLoading = false,
                isError = false,
                errorMessage = ""
            )
        }
    }

    fun setBreakWork(
        startBreakHour: Int,
        startBreakMinute: Int,
        endBreakHour: Int,
        endBreakMinute: Int
    ) {
        _workDayUiState.update {
            WorkDayUiState(
                dayWorkDayUIModel = it.dayWorkDayUIModel.copy(
                    startDayBreakTime = it.dayWorkDayUIModel.startDayBreakTime.withHour(startBreakHour)
                        .withMinute(startBreakMinute),
                    endDayBreakTime = it.dayWorkDayUIModel.endDayBreakTime.withHour(endBreakHour)
                        .withMinute(endBreakMinute)
                ),
                isLoading = false,
                isError = false,
                errorMessage = ""
            )
        }
    }
}