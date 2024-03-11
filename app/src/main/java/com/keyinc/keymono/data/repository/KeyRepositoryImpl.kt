package com.keyinc.keymono.data.repository

import com.keyinc.keymono.data.TokenStorage
import com.keyinc.keymono.data.api.KeyApi
import com.keyinc.keymono.data.api.UserApi
import com.keyinc.keymono.domain.entity.TransferRequests
import com.keyinc.keymono.domain.entity.UserKeyDto
import com.keyinc.keymono.domain.entity.UserPagedListDto
import com.keyinc.keymono.domain.repository.KeyRepository
import javax.inject.Inject

class KeyRepositoryImpl @Inject constructor(
    private val tokenStorage: TokenStorage,
    private val keyApi: KeyApi,
    private val userApi: UserApi
) : KeyRepository {

    private fun getBearerToken(): String {
        val token = tokenStorage.getToken()
        return "Bearer $token"
    }

    override suspend fun rejectTransfer(id: String) {
        keyApi.rejectTransfer(getBearerToken(), id)
    }

    override suspend fun approveTransfer(id: String) {
        keyApi.approveTransfer(getBearerToken(), id)
    }

    override suspend fun getUserAvailableKeys(): List<UserKeyDto> {
        val token = getBearerToken()
        return keyApi.getUserAvailableKeys(token)
    }

    override suspend fun getUserList(name: String): UserPagedListDto {
        val token = getBearerToken()
        return userApi.getUserList(token, name, 1, 10)
    }

    override suspend fun transferKeyForUser(id: String, userId: String) {
        val token = getBearerToken()
        keyApi.transferKeyForUser(token, id, userId)
    }

    override suspend fun getTransferRequests(
        status: String?,
        page: Int?,
        size: Int?
    ): TransferRequests {
        return keyApi.getTransferRequests(
            getBearerToken(),
            page,
            size
        )
    }

}
