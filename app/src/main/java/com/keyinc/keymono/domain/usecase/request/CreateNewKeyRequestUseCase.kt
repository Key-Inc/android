package com.keyinc.keymono.domain.usecase.request

import com.keyinc.keymono.domain.entity.KeyRequestCreateDto
import com.keyinc.keymono.domain.repository.RequestRepository
import javax.inject.Inject

class CreateNewKeyRequestUseCase @Inject constructor(
    private val requestRepository: RequestRepository
) {

    suspend operator fun invoke(keyRequestCreateDto: KeyRequestCreateDto)
        = requestRepository.createNewKeyRequest(keyRequestCreateDto)

}