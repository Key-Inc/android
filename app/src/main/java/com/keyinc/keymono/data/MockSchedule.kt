package com.keyinc.keymono.data

import com.keyinc.keymono.domain.entity.ScheduleElementDto
import com.keyinc.keymono.domain.entity.ScheduleStatus
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val formatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")
private fun toLocalDateTime(dateString: String) = LocalDateTime.parse(dateString, formatter)

object MockSchedule {
    val schedule = listOf(
        ScheduleElementDto(
            startDate = toLocalDateTime("08:45 17.02.2024"),
            endDate = toLocalDateTime("14:45 17.02.2024"),
            status = ScheduleStatus.Available
        ),
        ScheduleElementDto(
            startDate = toLocalDateTime("14:45 17.02.2024"),
            endDate = toLocalDateTime("16:20 17.02.2024"),
            status = ScheduleStatus.Busy
        ),
        ScheduleElementDto(
            startDate = toLocalDateTime("16:20 17.02.2024"),
            endDate = toLocalDateTime("16:35 17.02.2024"),
            status = ScheduleStatus.Available
        ),
        ScheduleElementDto(
            startDate = toLocalDateTime("16:35 17.02.2024"),
            endDate = toLocalDateTime("18:10 17.02.2024"),
            status = ScheduleStatus.Busy
        ),
        ScheduleElementDto(
            startDate = toLocalDateTime("18:10 17.02.2024"),
            endDate = toLocalDateTime("20:00 17.02.2024"),
            status = ScheduleStatus.Available
        ),
    )
}