package com.keyinc.keymono.data.api

import com.keyinc.keymono.data.api.NetworkConstants.KEY_SERVICE_URL
import com.keyinc.keymono.domain.entity.TransferRequests
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KeyApi {

    @GET("$KEY_SERVICE_URL/transfer-requests")
    suspend fun getTransferRequests(
        @Header("Authorization") token: String,
        @Query("status") status: String?,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
    ): TransferRequests

}
