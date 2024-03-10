package com.keyinc.keymono.domain.usecase.account

import com.keyinc.keymono.domain.repository.AccountRepository
import javax.inject.Inject

class GetUserRoleUseCase @Inject constructor(private val accountRepository: AccountRepository) {

    suspend fun execute(): String {
        return accountRepository.getUserRole()
    }
}