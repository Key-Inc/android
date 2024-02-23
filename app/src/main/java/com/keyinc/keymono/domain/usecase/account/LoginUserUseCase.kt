package com.keyinc.keymono.domain.usecase.account

import com.keyinc.keymono.domain.entity.LoginRequest
import com.keyinc.keymono.domain.repository.AccountRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    suspend operator fun invoke(loginRequest: LoginRequest)
        = accountRepository.login(loginRequest)

}