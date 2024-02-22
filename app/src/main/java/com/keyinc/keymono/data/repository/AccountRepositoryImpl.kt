package com.keyinc.keymono.data.repository

import android.util.Log
import com.keyinc.keymono.data.TokenStorage
import com.keyinc.keymono.data.api.AccountApi
import com.keyinc.keymono.domain.entity.LoginRequest
import com.keyinc.keymono.domain.entity.ProfileResponse
import com.keyinc.keymono.domain.entity.RegistrationRequest
import com.keyinc.keymono.domain.repository.AccountRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepositoryImpl @Inject constructor(
    private val tokenStorage: TokenStorage,
    private val accountApi: AccountApi
) : AccountRepository {

    override suspend fun getRegistrationStatus(): String {
        val token = getTokenFromStorage()
        return accountApi.getRegistrationStatus("Bearer $token")
    }

    override suspend fun getProfile(): ProfileResponse {
        val token = getTokenFromStorage()
        return accountApi.getProfile("Bearer $token")
    }

    override suspend fun getTokenFromStorage(): String {
        return tokenStorage.getToken()
    }

    override suspend fun isUserLoggedIn(): Boolean {
        Log.d("tag", "isUserLoggedIn: ${tokenStorage.getToken()}")
        return tokenStorage.getToken().isNotEmpty()
    }

    override suspend fun saveTokenToStorage(token: String) {
        tokenStorage.saveToken(token)
    }

    override suspend fun register(registrationRequest: RegistrationRequest) {
        val tokenResponse = accountApi.register(registrationRequest)
        saveTokenToStorage(tokenResponse.token)
    }

    override suspend fun login(loginRequest: LoginRequest) {
        val tokenResponse = accountApi.login(loginRequest)
        saveTokenToStorage(tokenResponse.token)
    }

}