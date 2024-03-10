package com.keyinc.keymono.domain.repository

import com.keyinc.keymono.domain.entity.TransferRequests

interface KeyRepository {

    suspend fun getTransferRequests(
        status: String?,
        page: Int?,
        size: Int?
    ): TransferRequests

}