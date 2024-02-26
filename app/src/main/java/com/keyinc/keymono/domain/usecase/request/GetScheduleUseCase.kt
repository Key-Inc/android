package com.keyinc.keymono.domain.usecase.request

import com.keyinc.keymono.domain.repository.RequestRepository
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject

class GetScheduleUseCase @Inject constructor(
    private val requestRepository: RequestRepository
) {

    suspend operator fun invoke(classroomId: UUID, date: LocalDate)
        = requestRepository.getSchedule(classroomId, date)

}