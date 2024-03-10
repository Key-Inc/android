package com.keyinc.keymono.domain.usecase.transfer

import com.keyinc.keymono.domain.repository.KeyRepository
import javax.inject.Inject

class GetTransferRequestsUseCase @Inject constructor(
    private val keyRepository: KeyRepository
) {

    suspend operator fun invoke(
        status: String? = null,
        page: Int? = 1,
        size: Int? = 5
    )
        = keyRepository.getTransferRequests(status, page, size)

}