package com.keyinc.keymono.domain.repository

import com.keyinc.keymono.domain.entity.KeyRequestCreateDto
import com.keyinc.keymono.domain.entity.RequestDTO
import com.keyinc.keymono.domain.entity.ScheduleElementDto
import java.time.LocalDate
import java.util.UUID

interface RequestRepository {

    suspend fun getUserRequest(): List<RequestDTO>

    suspend fun getSchedule(classroomId: UUID, date: LocalDate): List<ScheduleElementDto>

    suspend fun createNewKeyRequest(keyRequestCreateDto: KeyRequestCreateDto)

}