package com.droidper.xtrajob.ui.view.newworkday

import com.droidper.xtrajob.core.common.Mapper
import com.droidper.xtrajob.domain.model.RecordDay
import com.droidper.xtrajob.ui.model.WorkDayUIModel
import javax.inject.Inject

class WorkDayUiModelToDomainMapper @Inject constructor(): Mapper<WorkDayUIModel, RecordDay> {
    override fun map(input: WorkDayUIModel): RecordDay {

        return RecordDay(
            startDay = input.startDayWorkTimeUiModel.toLocalDateTime(),
            endDay = input.endDayWorkTimeUiModel.toLocalDateTime(),
            startBreakWork = input.startDayBreakTimeUiModel.toLocalDateTime(),
            endBreakWork = input.endDayBreakTimeUiModel.toLocalDateTime(),
            observations = input.observation
        )
    }

}
