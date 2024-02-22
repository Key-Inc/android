package com.keyinc.keymono.domain.usecase.classroom

import com.keyinc.keymono.domain.repository.ClassroomRepository
import javax.inject.Inject

class GetClassroomsUseCase @Inject constructor(
    private val classroomRepository: ClassroomRepository
) {

    suspend operator fun invoke(
        number: Int? = null,
        building: Int? = null,
        page: Int? = null,
        size: Int? = null
    ) = try {
        Result.success(classroomRepository.getClassrooms(number, building, page, size))
    } catch (e: Exception) {
        Result.failure(e)
    }

}