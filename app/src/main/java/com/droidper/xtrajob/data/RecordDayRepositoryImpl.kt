package com.droidper.xtrajob.data

import com.droidper.xtrajob.core.common.Resource
import com.droidper.xtrajob.domain.model.CoreFailure
import com.droidper.xtrajob.data.model.mappers.DomainToRecordDayEntityMapper
import com.droidper.xtrajob.domain.RecordDayRepository
import com.droidper.xtrajob.domain.model.DomainFailure
import com.droidper.xtrajob.domain.model.RecordDay
import com.droidper.xtrajob.frameworks.roomdatabase.RecordDayDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecordDayRepositoryImpl @Inject constructor(
    private val recordDayDao: RecordDayDao,
    private val domainToRecordDayEntityMapper: DomainToRecordDayEntityMapper
): RecordDayRepository {
    override suspend fun addWorkDay(recordDay: RecordDay): Resource<CoreFailure,Long> {
        return withContext(Dispatchers.IO)  {
            try {
                val id = recordDayDao.saveRecordDay(domainToRecordDayEntityMapper.map(recordDay))
                if (id != -1L) {
                    Resource.Success(id)
                } else {
                    Resource.Error(CoreFailure.DatabaseError(code = -1, message = "Error al guardar el registro"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Error(CoreFailure.DatabaseError(code = e.hashCode(), message = e.message))
            }


        }
    }
}