package com.keyinc.keymono.data.repository

import com.keyinc.keymono.data.TokenStorage
import com.keyinc.keymono.data.api.RequestApi
import com.keyinc.keymono.domain.entity.KeyRequestCreateDto
import com.keyinc.keymono.domain.entity.RequestDTO
import com.keyinc.keymono.domain.entity.ScheduleElementDto
import com.keyinc.keymono.domain.repository.RequestRepository
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject

class RequestRepositoryImpl @Inject constructor(
    private val tokenStorage: TokenStorage,
    private val requestApi: RequestApi
) : RequestRepository {

    private fun getBearerToken(): String {
        val token = tokenStorage.getToken()
        return "Bearer $token"
    }

    override suspend fun getUserRequest(): List<RequestDTO> =
        requestApi.getMyRequest(getBearerToken())

    override suspend fun getSchedule(classroomId: UUID, date: LocalDate): List<ScheduleElementDto> =
        requestApi.getSchedule(getBearerToken(), classroomId, date)

    override suspend fun createNewKeyRequest(keyRequestCreateDto: KeyRequestCreateDto) =
        requestApi.createNewKeyRequest(getBearerToken(), keyRequestCreateDto)

}