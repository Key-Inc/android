package com.keyinc.keymono.domain.usecase.request

import com.keyinc.keymono.domain.entity.RequestDTO
import com.keyinc.keymono.domain.repository.RequestRepository
import javax.inject.Inject


class GetUserRequestUseCase @Inject constructor(private val requestRepository: RequestRepository) {

    suspend fun invoke(): List<RequestDTO> =
        requestRepository.getUserRequest()

}