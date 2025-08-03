package com.droidper.xtrajob.ui.view.newworkday

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidper.xtrajob.domain.usecase.SaveWorkDayUseCase
import com.droidper.xtrajob.ui.timepicker.TimerPickerMapper
import com.droidper.xtrajob.ui.timepicker.TimePickerUiState
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
    private val timerPickerMapper: TimerPickerMapper
) : ViewModel() {

    private val _timeInitPickerUiState: MutableStateFlow<TimePickerUiState> = MutableStateFlow(TimePickerUiState())
    val timeInitPickerUiState: StateFlow<TimePickerUiState> = _timeInitPickerUiState.asStateFlow()

    private val _timeFinPickerUiState: MutableStateFlow<TimePickerUiState> = MutableStateFlow(TimePickerUiState())
    val timeFinPickerUiState: StateFlow<TimePickerUiState> = _timeFinPickerUiState.asStateFlow()



    fun saveWorkDay(hourInit: String, hourFin: String, startBreak: String, endBreak: String, observation: String) {
        viewModelScope.launch {
            //saveWorkDayUseCase(SaveWorkDayUseCase.Params(RecordDay))
        }

    }
    fun updateTimeInit(hour: Int, minute: Int) {
        _timeInitPickerUiState.update { it.copy(timerPickerModel = timerPickerMapper.map(Pair(hour,minute))) }
    }
    fun updateTimeFin(hour: Int, minute: Int) {
        _timeFinPickerUiState.update { it.copy(timerPickerModel = timerPickerMapper.map(Pair(hour,minute))) }
    }

    fun updateTimeBreak(hour: Int, minute: Int) {
        /*TODO*/
    }
}