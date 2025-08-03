package com.droidper.xtrajob.domain

import com.droidper.xtrajob.core.common.Resource
import com.droidper.xtrajob.domain.model.CoreFailure
import com.droidper.xtrajob.domain.model.RecordDay

interface RecordDayRepository {
    suspend fun addWorkDay(recordDay: RecordDay): Resource<CoreFailure,Long>
}