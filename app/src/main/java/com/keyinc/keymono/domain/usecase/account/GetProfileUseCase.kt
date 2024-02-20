package com.keyinc.keymono.domain.usecase.account

import com.keyinc.keymono.domain.entity.ProfileResponse
import com.keyinc.keymono.domain.repository.AccountRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(): ProfileResponse {
        return accountRepository.getProfile()
    }
}