package com.droidper.xtrajob.ui.view.newworkday

import com.droidper.xtrajob.core.common.Mapper
import com.droidper.xtrajob.ui.timepicker.TimeUiModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class DatePickerMapper: Mapper<Long, TimeUiModel> {
    override fun map(input: Long): TimeUiModel {
        return TimeUiModel(
            dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(input), ZoneId.systemDefault())
        )

    }

}
