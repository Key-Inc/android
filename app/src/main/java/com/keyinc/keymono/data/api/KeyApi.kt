package com.keyinc.keymono.data.api

import com.keyinc.keymono.data.api.NetworkConstants.KEY_SERVICE_URL
import com.keyinc.keymono.domain.entity.TransferRequests
import com.keyinc.keymono.domain.entity.UserKeyDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface KeyApi {

    @GET("$KEY_SERVICE_URL/transfer-requests")
    suspend fun getTransferRequests(
        @Header("Authorization") token: String,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
    ): TransferRequests


    @GET("$KEY_SERVICE_URL/my")
    suspend fun getUserAvailableKeys(@Header("Authorization") token: String): List<UserKeyDto>

    @POST("$KEY_SERVICE_URL/{id}/user/{userId}/transfer")
    suspend fun transferKeyForUser(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Path("userId") userId: String
    )

    @PUT("$KEY_SERVICE_URL/{id}/approve-transfer")
    suspend fun approveTransfer(
        @Header("Authorization") token: String,
        @Path("id") id: String
    )

    @PUT("$KEY_SERVICE_URL/{id}/reject-transfer")
    suspend fun rejectTransfer(
        @Header("Authorization") token: String,
        @Path("id") id: String
    )

}