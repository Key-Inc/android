package com.keyinc.keymono.domain.usecase

import com.keyinc.keymono.domain.repository.AuthRepository
import javax.inject.Inject

class GetTokenFromStorageUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend operator fun invoke(): String = authRepository.getTokenFromStorage()

}