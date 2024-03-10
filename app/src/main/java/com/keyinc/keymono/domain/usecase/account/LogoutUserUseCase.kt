package com.keyinc.keymono.domain.usecase.account

import com.keyinc.keymono.domain.repository.AccountRepository
import javax.inject.Inject

class LogoutUserUseCase @Inject constructor(private val accountRepository: AccountRepository) {

    fun execute() {
        accountRepository.clearToken()
    }
}