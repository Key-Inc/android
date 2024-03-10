package com.keyinc.keymono.data.api

import com.keyinc.keymono.data.api.NetworkConstants.KEY_SERVICE_URL
import com.keyinc.keymono.domain.entity.UserKeyDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface KeyApi {

    @GET("$KEY_SERVICE_URL/my")
    suspend fun getUserAvailableKeys(@Header("Authorization") token: String): List<UserKeyDto>

    @POST("$KEY_SERVICE_URL/{id}/user/{userId}/transfer")
    suspend fun transferKeyForUser(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Path("userId") userId: String
    )

}