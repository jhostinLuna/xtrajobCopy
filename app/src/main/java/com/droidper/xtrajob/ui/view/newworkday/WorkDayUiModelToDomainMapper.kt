package com.droidper.xtrajob.ui.view.newworkday

import com.droidper.xtrajob.core.common.Mapper
import com.droidper.xtrajob.domain.model.RecordDay
import com.droidper.xtrajob.ui.model.WorkDayUIModel
import javax.inject.Inject

class WorkDayUiModelToDomainMapper @Inject constructor(): Mapper<WorkDayUIModel, RecordDay> {
    override fun map(input: WorkDayUIModel): RecordDay {

        return RecordDay(
            startDay = input.startDayWorkTime,
            endDay = input.endDayWorkTime,
            startBreakWork = input.startDayBreakTime,
            endBreakWork = input.endDayBreakTime,
            observations = input.observation
        )
    }

}
