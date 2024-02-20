package com.keyinc.keymono.data.repository

import com.keyinc.keymono.data.TokenStorage
import com.keyinc.keymono.data.api.AccountApi
import com.keyinc.keymono.domain.entity.RegistrationRequest
import com.keyinc.keymono.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val tokenStorage: TokenStorage,
    private val accountApi: AccountApi
) :
    AuthRepository {

    override suspend fun getTokenFromStorage(): String {
        return tokenStorage.getToken()
    }

    override suspend fun saveTokenToStorage(token: String) {
        tokenStorage.saveToken(token)
    }

    override suspend fun register(registrationRequest: RegistrationRequest) {
        val tokenResponse = accountApi.register(registrationRequest)
        saveTokenToStorage(tokenResponse.token)
    }


}