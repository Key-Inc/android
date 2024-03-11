package com.keyinc.keymono.domain.repository

import com.keyinc.keymono.domain.entity.LoginRequest
import com.keyinc.keymono.domain.entity.ProfileResponse
import com.keyinc.keymono.domain.entity.RegistrationRequest
import com.keyinc.keymono.domain.entity.UserEditDto

interface AccountRepository {

    fun clearToken()

    suspend fun getProfile(): ProfileResponse

    suspend fun editProfile(profile: UserEditDto)

    suspend fun getUserRole(): String?

    suspend fun getRegistrationStatus(): String

    suspend fun getTokenFromStorage(): String

    suspend fun isUserLoggedIn(): Boolean

    suspend fun saveTokenToStorage(token: String)

    suspend fun register(registrationRequest: RegistrationRequest)

    suspend fun login(loginRequest: LoginRequest)

}