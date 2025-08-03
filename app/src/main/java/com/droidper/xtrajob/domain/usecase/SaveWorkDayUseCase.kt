package com.droidper.xtrajob.domain.usecase

import com.droidper.xtrajob.core.common.Resource
import com.droidper.xtrajob.domain.model.CoreFailure
import com.droidper.xtrajob.domain.RecordDayRepository
import com.droidper.xtrajob.domain.interactor.UseCase
import com.droidper.xtrajob.domain.model.RecordDay
import javax.inject.Inject

open class SaveWorkDayUseCase @Inject constructor(
    private val recordDayRepository: RecordDayRepository
): UseCase<SaveWorkDayUseCase.Params, Long>() {
    class Params(
        val recordDay: RecordDay
    )

    override suspend fun execute(parameters: Params): Resource<CoreFailure, Long> = recordDayRepository.addWorkDay(parameters.recordDay)
}