package com.keyinc.keymono.domain.usecase.account

import com.keyinc.keymono.domain.repository.AccountRepository
import javax.inject.Inject

class GetTokenFromStorageUseCase @Inject constructor(private val accountRepository: AccountRepository) {

    suspend operator fun invoke(): String = accountRepository.getTokenFromStorage()

}