package com.keyinc.keymono.domain.repository

import com.keyinc.keymono.domain.entity.TransferRequests
import com.keyinc.keymono.domain.entity.UserKeyDto
import com.keyinc.keymono.domain.entity.UserPagedListDto

interface KeyRepository {
    suspend fun getUserAvailableKeys(): List<UserKeyDto>

    suspend fun getUserList(name: String): UserPagedListDto

    suspend fun transferKeyForUser(id: String, userId: String)

    suspend fun getTransferRequests(
        status: String?,
        page: Int?,
        size: Int?
    ): TransferRequests

}