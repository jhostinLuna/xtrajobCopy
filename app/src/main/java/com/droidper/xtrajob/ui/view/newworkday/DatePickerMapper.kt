package com.droidper.xtrajob.ui.view.newworkday

import com.droidper.xtrajob.core.common.Mapper
import java.text.DateFormat
import java.util.Date
import java.util.Locale

class DatePickerMapper: Mapper<Long, String> {
    override fun map(input: Long): String {
        val date = Date(input)
        val df: DateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault())
        return df.format(date)
    }

}
