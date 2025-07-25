package com.droidper.xtrajob.data

import com.droidper.xtrajob.core.common.Resource
import com.droidper.xtrajob.core.common.Failure
import com.droidper.xtrajob.domain.RecordDayRepository
import com.droidper.xtrajob.domain.model.RecordDay
import com.droidper.xtrajob.frameworks.roomdatabase.RecordDayDao
import javax.inject.Inject

class RecordDayRepositoryImpl @Inject constructor(
    private val recordDayDao: RecordDayDao
): RecordDayRepository {
    override fun addWorkDay(recordDay: RecordDay): Resource<Failure,RecordDay> {
        TODO()
    }
}