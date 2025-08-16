package com.droidper.xtrajob.ui.view.newworkday

import com.droidper.xtrajob.core.common.Mapper
import com.droidper.xtrajob.domain.model.RecordDay
import com.droidper.xtrajob.ui.model.WorkDayUIModel
import javax.inject.Inject

class WorkDayUiModelToDomainMapper @Inject constructor(): Mapper<WorkDayUIModel, RecordDay> {
    override fun map(input: WorkDayUIModel): RecordDay {

        return RecordDay(
            startDay = input.startDayWorkTimeUiModel.dateTime,
            endDay = input.endDayWorkTimeUiModel.dateTime,
            startBreakWork = input.startDayBreakTimeUiModel.dateTime,
            endBreakWork = input.endDayBreakTimeUiModel.dateTime,
            observations = input.observation
        )
    }

}
