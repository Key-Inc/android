package com.keyinc.keymono.domain.usecase.account

import com.keyinc.keymono.domain.repository.AccountRepository
import javax.inject.Inject

class GetUserRequestStatus @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(): Boolean {
        return when (accountRepository.getRegistrationStatus()) {
            "Accepted" -> true
            else -> false
        }
    }
}