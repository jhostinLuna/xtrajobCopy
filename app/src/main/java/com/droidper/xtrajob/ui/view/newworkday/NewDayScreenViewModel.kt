package com.droidper.xtrajob.ui.view.newworkday

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidper.xtrajob.core.common.Resource
import com.droidper.xtrajob.core.extensions.timeMillisToLocalDateTime
import com.droidper.xtrajob.domain.model.CoreFailure
import com.droidper.xtrajob.domain.model.RecordDay
import com.droidper.xtrajob.domain.usecase.SaveWorkDayUseCase
import com.droidper.xtrajob.ui.model.DateTimeUiState
import com.droidper.xtrajob.ui.model.SaveStatusUiState
import com.droidper.xtrajob.ui.model.TimeBreakWorkUiState
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
) : ViewModel() {
    private val tag = this@NewDayScreenViewModel.javaClass.name

    private val _startWorkTimeUiState: MutableStateFlow<DateTimeUiState> = MutableStateFlow(
        DateTimeUiState()
    )
    val startWorkTimeUiState: StateFlow<DateTimeUiState> = _startWorkTimeUiState.asStateFlow()

    private val _endWorkTimeUiState: MutableStateFlow<DateTimeUiState> = MutableStateFlow(
        DateTimeUiState()
    )
    val endWorkTimeUiState: StateFlow<DateTimeUiState> = _endWorkTimeUiState.asStateFlow()

    private val _startBreakTimeUiState: MutableStateFlow<DateTimeUiState> = MutableStateFlow(
        DateTimeUiState()
    )
    val startBreakTimeUiState: StateFlow<DateTimeUiState> = _startBreakTimeUiState.asStateFlow()

    private val _endBreakTimeUiState: MutableStateFlow<DateTimeUiState> = MutableStateFlow(
        DateTimeUiState()
    )
    val endBreakTimeUiState: StateFlow<DateTimeUiState> = _endBreakTimeUiState.asStateFlow()

    private val _isBreakWorkUiState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isBreakWorkUiState: StateFlow<Boolean> = _isBreakWorkUiState.asStateFlow()

    private val _observationUiState: MutableStateFlow<String> = MutableStateFlow("")
    val observationUiState: StateFlow<String> = _observationUiState.asStateFlow()

    private val _saveWorkDayUiState: MutableStateFlow<SaveStatusUiState> = MutableStateFlow(SaveStatusUiState())
    val saveWorkDayUiState: StateFlow<SaveStatusUiState> = _saveWorkDayUiState.asStateFlow()

    private val _timeBreakWorkUiState: MutableStateFlow<TimeBreakWorkUiState> = MutableStateFlow(
        TimeBreakWorkUiState()
    )
    val timeBreakWorkUiState: StateFlow<TimeBreakWorkUiState> = _timeBreakWorkUiState.asStateFlow()

    fun saveWorkDay() {
        viewModelScope.launch {
            _saveWorkDayUiState.value = SaveStatusUiState(
                isLoading = true
            )
            saveWorkDayUseCase(
                SaveWorkDayUseCase.Params(
                    RecordDay(
                        startDay = startWorkTimeUiState.value.dateTime,
                        endDay = endWorkTimeUiState.value.dateTime,
                        startBreakWork = startBreakTimeUiState.value.dateTime,
                        endBreakWork = endBreakTimeUiState.value.dateTime,
                        observations = observationUiState.value
                    )
                )
            ){resource->
                when(resource) {
                    is Resource.Error -> {
                        when(resource.error) {
                            is CoreFailure.DatabaseError -> {
                                _saveWorkDayUiState.value = SaveStatusUiState(
                                    isError = true,
                                    errorMessage = resource.error.message?: ""
                                )
                                Log.d(tag, "saveWorkDay: Error Database")
                            }
                            else -> {
                                Log.d(tag, "saveWorkDay: Error")
                            }

                        }
                    }
                    is Resource.Success -> {
                        _saveWorkDayUiState.update {
                            it.copy(
                                isLoading = false,
                            )
                        }
                    }
                }
            }
        }

    }
    fun updateStartWorkTime(hour: Int, minute: Int) {
        _startWorkTimeUiState.update {

            val startDateTime = it.dateTime
                .withHour(hour)
                .withMinute(minute)
            if (startDateTime.isAfter(endWorkTimeUiState.value.dateTime)) {
                DateTimeUiState(
                    dateTime = startDateTime,
                    isError = true,
                    errorMessage = "La hora de inicio no puede ser posterior a la hora de fin"
                )
            } else {
                it.copy(
                    dateTime = startDateTime
                )

            }
        }
    }

    fun updateEndWorkTime(hour: Int, minute: Int) {
        _endWorkTimeUiState.update {
            val endDateTime = it.dateTime
                .withHour(hour)
                .withMinute(minute)
            if (endDateTime.isBefore(startWorkTimeUiState.value.dateTime)) {
                DateTimeUiState(
                    dateTime = endDateTime,
                    isError = true,
                    errorMessage = "La hora de fin no puede ser anterior a la hora de inicio"
                )
            } else {
                it.copy(
                    dateTime = endDateTime
                )
            }
        }
    }

    fun changeBreakWorkState() {
        _isBreakWorkUiState.value = !_isBreakWorkUiState.value
        if (!_isBreakWorkUiState.value) {
            _startBreakTimeUiState.value = DateTimeUiState()
            _endBreakTimeUiState.value = DateTimeUiState()
        }

    }

    /**
     * Actualiza la fecha de inicio de la jornada laboral
     */
    fun updateDateStartWorkDay(dateMillis: Long) {
        _startWorkTimeUiState.update {
            val startDateTime = dateMillis.timeMillisToLocalDateTime()
                .withHour(it.dateTime.hour)
                .withMinute(it.dateTime.minute)
            if (startDateTime.isAfter(_endWorkTimeUiState.value.dateTime)) {
                DateTimeUiState(
                    dateTime = startDateTime,
                    isError = true,
                    errorMessage = "La hora de inicio no puede ser posterior a la hora de fin"
                )
            } else {
                it.copy(
                    dateTime = startDateTime
                )
            }

        }
    }

    /**
     * Actualiza la fecha de fin de la jornada laboral
     */
    fun updateDateEndWorkDay(dateMillis: Long) {
        _endWorkTimeUiState.update {
            val endDateTime = dateMillis.timeMillisToLocalDateTime()
                .withHour(it.dateTime.hour)
                .withMinute(it.dateTime.minute)
            if (endDateTime.isBefore(_startWorkTimeUiState.value.dateTime)) {
                DateTimeUiState(
                    dateTime = endDateTime,
                    isError = true,
                    errorMessage = "La hora de fin no puede ser anterior a la hora de inicio"
                )
            } else {
                it.copy(
                    dateTime = endDateTime
                )
            }
        }
        checkBreakWorkTime()
    }

    fun updateStartBreakTimeUiState(
        startBreakHour: Int,
        startBreakMinute: Int
    ) {

        _startBreakTimeUiState.update {
            it.copy(
                dateTime = it.dateTime.withHour(startBreakHour).withMinute(startBreakMinute)
            )

        }
        checkBreakWorkTime()

    }

    fun checkBreakWorkTime(): Boolean {
        var isOk = false
        when {

            _startBreakTimeUiState.value.dateTime.isAfter(_endWorkTimeUiState.value.dateTime) -> {
                isOk = true
                _startBreakTimeUiState.update {
                    DateTimeUiState(
                        dateTime = it.dateTime,
                        isError = true,
                        errorMessage = "La hora de inicio de descanso no puede ser posterior a la hora de fin de descanso"
                    )
                }
            }
            _endBreakTimeUiState.value.dateTime.isBefore(_startWorkTimeUiState.value.dateTime) -> {
                isOk = true
                _endBreakTimeUiState.update {
                    DateTimeUiState(
                        dateTime = it.dateTime,
                        isError = true,
                        errorMessage = "La hora de fin de descanso no puede ser anterior a la hora de inicio de descanso"
                    )
                }
            }


        }
        return isOk
    }

    fun updateObservationUiState(observation: String) {
        if (observation.isNotEmpty()) {
            _observationUiState.update {
                observation
            }
        }

    }

    fun updateTimeBreakWorkUiState(time: Int, isMinutes: Boolean = false) {
        _timeBreakWorkUiState.update {
            it.copy(
                timeBreakWork = time
            )
        }
        _endBreakTimeUiState.update {
            if (isMinutes) {
                it.copy(
                    dateTime = _startBreakTimeUiState.value.dateTime.plusMinutes(time.toLong())
                )
            } else {
                it.copy(
                    dateTime = _startBreakTimeUiState.value.dateTime.plusHours(time.toLong())
                )
            }
        }
        checkBreakWorkTime()
    }
}