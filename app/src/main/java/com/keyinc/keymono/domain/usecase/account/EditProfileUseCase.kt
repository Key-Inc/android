package com.keyinc.keymono.domain.usecase.account

import com.keyinc.keymono.domain.entity.UserEditDto
import com.keyinc.keymono.domain.repository.AccountRepository
import javax.inject.Inject

class EditProfileUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    private companion object {
        const val PHONE_NUMBER_PREFIX = "+7"
    }

    suspend operator fun invoke(profile: UserEditDto) {
        val updatedRequest = profile.copy(
            phoneNumber = PHONE_NUMBER_PREFIX + profile.phoneNumber
        )
        accountRepository.editProfile(updatedRequest)
    }

}