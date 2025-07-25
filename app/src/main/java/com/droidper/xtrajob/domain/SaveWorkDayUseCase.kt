package com.droidper.xtrajob.domain

import com.droidper.xtrajob.core.common.Resource
import com.droidper.xtrajob.core.common.Failure
import com.droidper.xtrajob.domain.interactor.UseCase
import com.droidper.xtrajob.domain.model.RecordDay
import javax.inject.Inject

class SaveWorkDayUseCase @Inject constructor(
    private val recordDayRepository: RecordDayRepository
): UseCase<SaveWorkDayUseCase.Params, RecordDay>() {
    class Params(
        val recordDay: RecordDay
    )

    override fun execute(parameters: Params): Resource<Failure, RecordDay> = recordDayRepository.addWorkDay(parameters.recordDay)
}