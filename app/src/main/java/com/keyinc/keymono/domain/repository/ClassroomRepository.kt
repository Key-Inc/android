package com.keyinc.keymono.domain.repository

import com.keyinc.keymono.domain.entity.Classroom

interface ClassroomRepository {

    suspend fun getClassrooms(
        number: Int?,
        building: Int?,
        page: Int?,
        size: Int?
    ): List<Classroom>

}