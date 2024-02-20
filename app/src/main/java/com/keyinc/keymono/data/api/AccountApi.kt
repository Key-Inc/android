package com.keyinc.keymono.data.api

import com.keyinc.keymono.data.api.NetworkConstants.ACCOUNT_SERVICE_URL
import com.keyinc.keymono.domain.entity.RegistrationRequest
import com.keyinc.keymono.domain.entity.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface AccountApi {

    @POST("$ACCOUNT_SERVICE_URL/register")
    suspend fun register(@Body registrationRequest: RegistrationRequest): TokenResponse
}