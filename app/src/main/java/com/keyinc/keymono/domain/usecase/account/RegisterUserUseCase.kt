package com.keyinc.keymono.domain.usecase.account

import com.keyinc.keymono.domain.entity.RegistrationRequest
import com.keyinc.keymono.domain.repository.AccountRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val authRepository: AccountRepository) {

    suspend operator fun invoke(
        email: String,
        fullName: String,
        password: String,
        birthDate: String,
        phoneNumber: String,
    ) {
        val registrationRequest = RegistrationRequest(
            email = email,
            fullName = fullName,
            password = password,
            birthDate = "2024-02-20T09:11:17.004Z" ,
            phoneNumber = "+7$phoneNumber"
        )
        authRepository.register(registrationRequest)
    }
}
