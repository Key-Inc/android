package com.keyinc.keymono.domain.repository

import com.keyinc.keymono.domain.entity.ScheduleElementDto
import java.time.LocalDate
import java.util.UUID

interface RequestRepository {

    suspend fun getSchedule(classroomId: UUID, date: LocalDate): List<ScheduleElementDto>

}