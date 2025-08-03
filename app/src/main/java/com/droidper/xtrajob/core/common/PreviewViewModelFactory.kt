package com.droidper.xtrajob.core.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.droidper.xtrajob.domain.RecordDayRepository
import com.droidper.xtrajob.domain.model.CoreFailure
import com.droidper.xtrajob.domain.model.RecordDay
import com.droidper.xtrajob.domain.usecase.SaveWorkDayUseCase
import com.droidper.xtrajob.ui.timepicker.TimerPickerMapper
import com.droidper.xtrajob.ui.view.newworkday.NewDayScreenViewModel

class PreviewViewModelFactory: ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewDayScreenViewModel::class.java)) {

            // Crea una instancia falsa de RecordDayRepository
            val dummyRecordDayRepository = object : RecordDayRepository {
                override suspend fun addWorkDay(recordDay: RecordDay): Resource<CoreFailure, Long> {
                    // Devuelve un resultado de éxito para la preview
                    return Resource.Success(1L)
                }
            }

            // Usamos el dummyRecordDayRepository para crear el dummySaveWorkDayUseCase
            val dummySaveWorkDayUseCase = object : SaveWorkDayUseCase(dummyRecordDayRepository) {
                override suspend fun execute(parameters: Params): Resource<CoreFailure, Long> {
                    // Llama al método del repositorio de prueba
                    return dummyRecordDayRepository.addWorkDay(parameters.recordDay)
                }
            }
            return NewDayScreenViewModel(
                saveWorkDayUseCase = SaveWorkDayUseCase(dummyRecordDayRepository),
                timerPickerMapper = TimerPickerMapper()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}