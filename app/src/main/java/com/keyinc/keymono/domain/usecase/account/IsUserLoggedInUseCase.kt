package com.keyinc.keymono.domain.usecase.account

import com.keyinc.keymono.domain.repository.AccountRepository
import javax.inject.Inject

class IsUserLoggedInUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(): Boolean {
        return accountRepository.isUserLoggedIn()
    }
}