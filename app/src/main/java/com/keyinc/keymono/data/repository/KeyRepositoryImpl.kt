package com.keyinc.keymono.data.repository

import com.keyinc.keymono.data.TokenStorage
import com.keyinc.keymono.data.api.KeyApi
import com.keyinc.keymono.domain.entity.TransferRequests
import com.keyinc.keymono.domain.repository.KeyRepository
import javax.inject.Inject

class KeyRepositoryImpl @Inject constructor(
    private val keyApi: KeyApi,
    private val tokenStorage: TokenStorage
) : KeyRepository {

    private fun getBearerToken(): String {
        val token = tokenStorage.getToken()
        return "Bearer $token"
    }

    override suspend fun getTransferRequests(
        status: String?,
        page: Int?,
        size: Int?
    ): TransferRequests {
        return keyApi.getTransferRequests(
            getBearerToken(),
            status,
            page,
            size
        )
    }

}