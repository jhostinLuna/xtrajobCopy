package com.droidper.xtrajob.domain

import com.droidper.xtrajob.core.common.Resource
import com.droidper.xtrajob.core.common.Failure
import com.droidper.xtrajob.domain.model.RecordDay

interface RecordDayRepository {
    fun addWorkDay(recordDay: RecordDay): Resource<Failure,RecordDay>
}