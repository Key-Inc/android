package com.keyinc.keymono.domain.usecase.account

import com.keyinc.keymono.domain.entity.RegistrationRequest
import com.keyinc.keymono.domain.repository.AccountRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val accountRepository: AccountRepository) {

    private companion object {
        const val PHONE_NUMBER_PREFIX = "+7"
    }

    suspend operator fun invoke(
        registrationRequest: RegistrationRequest
    ) {
        val updatedRequest = registrationRequest.copy(
            phoneNumber = PHONE_NUMBER_PREFIX + registrationRequest.phoneNumber
        )
        accountRepository.register(updatedRequest)
    }
}