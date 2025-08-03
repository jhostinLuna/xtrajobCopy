package com.droidper.xtrajob.ui.timepicker

import com.droidper.xtrajob.core.common.Mapper
import java.sql.Time
import java.util.Locale
import javax.inject.Inject


class TimerPickerMapper @Inject constructor(): Mapper<Pair<Int,Int>,TimeUiModel> {
    override fun map(input: Pair<Int, Int>): TimeUiModel {
        return TimeUiModel(
            hour = String.format(Locale.getDefault(),"%02d",input.first),
            minute = String.format(Locale.getDefault(),"%02d",input.second))
    }
}