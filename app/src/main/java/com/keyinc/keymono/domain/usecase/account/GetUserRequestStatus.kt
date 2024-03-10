package com.keyinc.keymono.domain.usecase.account

import com.keyinc.keymono.domain.repository.AccountRepository
import javax.inject.Inject

class GetUserRequestStatus @Inject constructor(
    private val accountRepository: AccountRepository
) {

    private companion object {
        private const val ACCEPTED_STATUS = "Accepted"
    }

    suspend operator fun invoke(): Boolean {
        return when (accountRepository.getRegistrationStatus()) {
            ACCEPTED_STATUS -> true
            else -> false
        }
    }

}