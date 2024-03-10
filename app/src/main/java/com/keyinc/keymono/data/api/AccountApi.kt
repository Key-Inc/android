package com.keyinc.keymono.data.api

import com.keyinc.keymono.data.api.NetworkConstants.ACCOUNT_SERVICE_URL
import com.keyinc.keymono.data.entity.TokenResponse
import com.keyinc.keymono.domain.entity.LoginRequest
import com.keyinc.keymono.domain.entity.ProfileResponse
import com.keyinc.keymono.domain.entity.RegistrationRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface AccountApi {

    @POST("$ACCOUNT_SERVICE_URL/register")
    suspend fun register(@Body registrationRequest: RegistrationRequest): TokenResponse

    @POST("$ACCOUNT_SERVICE_URL/login")
    suspend fun login(@Body loginRequest: LoginRequest): TokenResponse

    @GET("$ACCOUNT_SERVICE_URL/role")
    suspend fun getUserRole(@Header("Authorization") token: String): String

    @GET("$ACCOUNT_SERVICE_URL/profile")
    suspend fun getProfile(@Header("Authorization") token: String): ProfileResponse

    @GET("$ACCOUNT_SERVICE_URL/registration-status")
    suspend fun getRegistrationStatus(@Header("Authorization") token: String): String
}