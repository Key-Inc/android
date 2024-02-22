package com.keyinc.keymono.data.model

import com.keyinc.keymono.domain.entity.Classroom
import com.keyinc.keymono.domain.entity.PageInfoDto

data class ClassroomPagedListDto(
    val classrooms: List<Classroom>,
    val pagination: PageInfoDto
)