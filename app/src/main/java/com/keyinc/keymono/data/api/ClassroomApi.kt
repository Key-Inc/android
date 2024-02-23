package com.keyinc.keymono.data.api

import com.keyinc.keymono.data.api.NetworkConstants.CLASSROOM_SERVICE_URL
import com.keyinc.keymono.data.model.ClassroomPagedListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ClassroomApi {

    @GET(CLASSROOM_SERVICE_URL)
    suspend fun getClassrooms(
        @Query("number") number: Int? = null,
        @Query("building") building: Int? = null,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null
    ): ClassroomPagedListDto

}