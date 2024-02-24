package com.keyinc.keymono.data.api

import com.keyinc.keymono.data.api.NetworkConstants.REQUEST_SERVICE_URL
import com.keyinc.keymono.domain.entity.ScheduleElementDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.time.LocalDate
import java.util.UUID

interface RequestApi {

    @GET("$REQUEST_SERVICE_URL/schedule")
    suspend fun getSchedule(
        @Header("Authorization") token: String,
        @Query("classroomId") classroomId: UUID,
        @Query("date") date: LocalDate
    ): List<ScheduleElementDto>

}