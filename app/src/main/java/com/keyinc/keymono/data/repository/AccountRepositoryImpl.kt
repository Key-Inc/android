package com.keyinc.keymono.data.repository

import com.keyinc.keymono.data.TokenStorage
import com.keyinc.keymono.data.api.AccountApi
import com.keyinc.keymono.domain.entity.LoginRequest
import com.keyinc.keymono.domain.entity.ProfileResponse
import com.keyinc.keymono.domain.entity.RegistrationRequest
import com.keyinc.keymono.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val tokenStorage: TokenStorage,
    private val accountApi: AccountApi
) : AccountRepository {

    private suspend fun getBearerToken(): String {
        val token = getTokenFromStorage()
        return "Bearer $token"
    }

    override suspend fun getRegistrationStatus(): String {
        return accountApi.getRegistrationStatus(getBearerToken())
    }

    override suspend fun getUserRole(): String {
        return accountApi.getUserRole(getBearerToken())
    }

    override fun clearToken() {
        tokenStorage.deleteToken()
    }

    override suspend fun getProfile(): ProfileResponse {
        return accountApi.getProfile(getBearerToken())
    }

    override suspend fun getUserRole(): String {
        return accountApi.getUserRole(getBearerToken())
    }

    override suspend fun getTokenFromStorage(): String {
        return tokenStorage.getToken()
    }

    override suspend fun isUserLoggedIn(): Boolean {
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