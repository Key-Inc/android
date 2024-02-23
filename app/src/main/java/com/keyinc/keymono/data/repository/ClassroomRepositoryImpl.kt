package com.keyinc.keymono.data.repository

import com.keyinc.keymono.data.api.ClassroomApi
import com.keyinc.keymono.domain.repository.ClassroomRepository
import javax.inject.Inject

class ClassroomRepositoryImpl @Inject constructor(
    private val classroomApi: ClassroomApi
) : ClassroomRepository {

    // TODO produce flow?
    override suspend fun getClassrooms(
        number: Int?,
        building: Int?,
        page: Int?,
        size: Int?
    ) = classroomApi.getClassrooms(number, building, page, size).classrooms

}