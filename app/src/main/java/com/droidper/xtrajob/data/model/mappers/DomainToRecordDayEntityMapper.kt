package com.droidper.xtrajob.data.model.mappers

import com.droidper.xtrajob.core.common.Mapper
import com.droidper.xtrajob.data.model.RecordDayEntity
import com.droidper.xtrajob.domain.model.RecordDay
import java.time.ZoneId
import javax.inject.Inject

class DomainToRecordDayEntityMapper @Inject constructor(): Mapper<RecordDay,RecordDayEntity> {
    override fun map(input: RecordDay): RecordDayEntity {

        return RecordDayEntity(
            startDay = input.startDay.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            endDay = input.endDay.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            startBreakWork = input.startBreakWork.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            endBreakWork = input.endBreakWork.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            observations = input.observations
        )
    }
}