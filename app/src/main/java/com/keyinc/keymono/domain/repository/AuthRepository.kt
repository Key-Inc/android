package com.keyinc.keymono.domain.repository

interface AuthRepository {
    suspend fun getTokenFromStorage(): String
}