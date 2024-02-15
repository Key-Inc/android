package com.keyinc.keymono.data.repository

import com.keyinc.keymono.data.TokenStorage
import com.keyinc.keymono.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(private val tokenStorage: TokenStorage) :
    AuthRepository {

    override suspend fun getTokenFromStorage(): String {
        return tokenStorage.getToken()
    }

}