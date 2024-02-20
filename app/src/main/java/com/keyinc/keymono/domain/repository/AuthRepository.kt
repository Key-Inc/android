package com.keyinc.keymono.domain.repository

import com.keyinc.keymono.domain.entity.RegistrationRequest

interface AuthRepository {
    suspend fun getTokenFromStorage(): String
    suspend fun saveTokenToStorage(token: String)
    suspend fun register(registrationRequest: RegistrationRequest)
}