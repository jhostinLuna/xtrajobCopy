package com.droidper.xtrajob.ui.view.newworkday

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidper.xtrajob.domain.usecase.SaveWorkDayUseCase
import com.droidper.xtrajob.ui.model.WorkDayUIModel
import com.droidper.xtrajob.ui.timepicker.TimePickerMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NewDayScreenViewModel @Inject constructor(
    private val saveWorkDayUseCase: SaveWorkDayUseCase,
    private val timePickerMapper: TimePickerMapper,
    private val datePickerMapper: DatePickerMapper,
    private val workDayUiModelToDomainMapper: WorkDayUiModelToDomainMapper
) : ViewModel() {

    private val _workDayUiState: MutableStateFlow<WorkDayUiState> = MutableStateFlow(WorkDayUiState())
    val workDayUiState: StateFlow<WorkDayUiState> = _workDayUiState.asStateFlow()

    init {
        val dateTimeNow = Date().time
        updateDateStartWorkDay(dateTimeNow)
        updateDateEndWorkDay(dateTimeNow)
    }

    fun saveWorkDay() {
        viewModelScope.launch {
            saveWorkDayUseCase(SaveWorkDayUseCase.Params(workDayUiModelToDomainMapper.map(_workDayUiState.value.dayWorkDayUIModel)))
        }

    }
    fun updateTimeWorkStart(hour: Int, minute: Int) {
        _workDayUiState.update {
            WorkDayUiState(
                dayWorkDayUIModel = it.dayWorkDayUIModel.copy(startDayWorkTimeUiModel = timePickerMapper.map(Pair(hour,minute))),
                isLoading = false,
                isError = false,
                errorMessage = ""
            )
        }
    }
    fun updateTimeWorkEnd(hour: Int, minute: Int) {
        _workDayUiState.update {
            WorkDayUiState(
                dayWorkDayUIModel = it.dayWorkDayUIModel.copy(endDayWorkTimeUiModel = timePickerMapper.map(Pair(hour,minute))),
                isLoading = false,
                isError = false,
                errorMessage = ""
            )
        }
    }

    fun updateTimeBreakStart(hour: Int, minute: Int) {
        _workDayUiState.update {
            WorkDayUiState(
                dayWorkDayUIModel = it.dayWorkDayUIModel.copy(startDayBreakTimeUiModel = timePickerMapper.map(Pair(hour,minute))),
                isLoading = false,
                isError = false,
                errorMessage = ""
            )
        }

    }

    fun updateTimeBreakEnd(hour: Int, minute: Int) {
        _workDayUiState.update { it.copy(dayWorkDayUIModel = WorkDayUIModel(endDayBreakTimeUiModel = timePickerMapper.map(Pair(hour,minute)))) }
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
                dayWorkDayUIModel = it.dayWorkDayUIModel.copy(dateStartWorkday = datePickerMapper.map(dateMillis)),
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
                dayWorkDayUIModel = it.dayWorkDayUIModel.copy(dateEndWorkday = datePickerMapper.map(dateMillis)),
                isLoading = false,
                isError = false,
                errorMessage = ""
            )
        }
    }
}