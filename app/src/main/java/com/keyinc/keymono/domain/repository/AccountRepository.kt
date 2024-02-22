package com.keyinc.keymono.domain.repository

import com.keyinc.keymono.domain.entity.LoginRequest
import com.keyinc.keymono.domain.entity.ProfileResponse
import com.keyinc.keymono.domain.entity.RegistrationRequest

interface AccountRepository {

    suspend fun getProfile(): ProfileResponse

    suspend fun getRegistrationStatus(): String

    suspend fun getTokenFromStorage(): String

    suspend fun isUserLoggedIn(): Boolean

    suspend fun saveTokenToStorage(token: String)

    suspend fun register(registrationRequest: RegistrationRequest)

    suspend fun login(loginRequest: LoginRequest)

}