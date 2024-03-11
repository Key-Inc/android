package com.keyinc.keymono.domain.usecase.key

import com.keyinc.keymono.domain.repository.KeyRepository
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(private val keyRepository: KeyRepository) {

    suspend operator fun invoke(name: String) = keyRepository.getUserList(name)

}