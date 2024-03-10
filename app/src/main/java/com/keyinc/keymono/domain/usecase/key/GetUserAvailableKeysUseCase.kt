package com.keyinc.keymono.domain.usecase.key

import com.keyinc.keymono.domain.entity.UserKeyDto
import com.keyinc.keymono.domain.repository.KeyRepository
import javax.inject.Inject

class GetUserAvailableKeysUseCase @Inject constructor(private val keyRepository: KeyRepository) {

    suspend fun invoke(): List<UserKeyDto> = keyRepository.getUserAvailableKeys()
}