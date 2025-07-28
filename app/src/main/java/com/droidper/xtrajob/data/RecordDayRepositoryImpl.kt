package com.droidper.xtrajob.data

import com.droidper.xtrajob.core.common.Resource
import com.droidper.xtrajob.domain.model.CoreFailure
import com.droidper.xtrajob.data.model.mappers.DomainToRecordDayEntityMapper
import com.droidper.xtrajob.domain.RecordDayRepository
import com.droidper.xtrajob.domain.model.DomainFailure
import com.droidper.xtrajob.domain.model.RecordDay
import com.droidper.xtrajob.frameworks.roomdatabase.RecordDayDao
import javax.inject.Inject

class RecordDayRepositoryImpl @Inject constructor(
    private val recordDayDao: RecordDayDao,
    private val domainToRecordDayEntityMapper: DomainToRecordDayEntityMapper
): RecordDayRepository {
    override fun addWorkDay(recordDay: RecordDay): Resource<CoreFailure,Long> {
        return try {
            val id = recordDayDao.saveRecordDay(domainToRecordDayEntityMapper.map(recordDay))
            if (id != -1L) {
                return Resource.Success(id)
            } else {
                Resource.Error(DomainFailure.AbortInsertDataBaseError)
            }

        }catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(CoreFailure.DatabaseError(code = e.hashCode(), message = e.message))
        }
    }
}