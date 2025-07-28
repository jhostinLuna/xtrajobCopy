package com.droidper.xtrajob.data.model.mappers

import com.droidper.xtrajob.core.common.Mapper
import com.droidper.xtrajob.data.model.RecordDayEntity
import com.droidper.xtrajob.domain.model.RecordDay
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

class RecordDayEntityToDomainMapper @Inject constructor(): Mapper<RecordDayEntity,RecordDay> {
    override fun map(input: RecordDayEntity): RecordDay {
        val timestampStartDay = Instant.ofEpochMilli(input.startDay)
        val timestampEndDay = Instant.ofEpochMilli(input.endDay)
        val timestampStartBreakWork = Instant.ofEpochMilli(input.startBreakWork)
        val timestampEndBreakWork = Instant.ofEpochMilli(input.endBreakWork)
        return RecordDay(
            startDay = LocalDateTime.ofInstant(timestampStartDay,ZoneId.systemDefault()),
            endDay = LocalDateTime.ofInstant(timestampEndDay, ZoneId.systemDefault()),
            startBreakWork = LocalDateTime.ofInstant(timestampStartBreakWork, ZoneId.systemDefault()),
            endBreakWork = LocalDateTime.ofInstant(timestampEndBreakWork, ZoneId.systemDefault()),
            observations = input.observations
        )
    }
}