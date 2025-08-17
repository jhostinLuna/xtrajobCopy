package com.droidper.xtrajob.core.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.droidper.xtrajob.domain.RecordDayRepository
import com.droidper.xtrajob.domain.model.CoreFailure
import com.droidper.xtrajob.domain.model.RecordDay
import com.droidper.xtrajob.domain.usecase.SaveWorkDayUseCase
import com.droidper.xtrajob.ui.view.newworkday.NewDayScreenViewModel
import com.droidper.xtrajob.ui.view.newworkday.WorkDayUiModelToDomainMapper

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
            return NewDayScreenViewModel(
                saveWorkDayUseCase = SaveWorkDayUseCase(dummyRecordDayRepository),
                workDayUiModelToDomainMapper = WorkDayUiModelToDomainMapper()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}