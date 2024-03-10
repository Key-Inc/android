package com.keyinc.keymono.domain.usecase.key

import com.keyinc.keymono.domain.repository.KeyRepository
import javax.inject.Inject

class TransferKeyForUserUseCase @Inject constructor(private val keyRepository: KeyRepository) {

    suspend operator fun invoke(id: String, userId: String) {
        keyRepository.transferKeyForUser(id, userId)
    }

}