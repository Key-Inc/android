package com.keyinc.keymono.data.api

import com.keyinc.keymono.domain.entity.UserPagedListDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UserApi {

    @GET("api/user")
    suspend fun getUserList(
        @Header("Authorization") token: String,
        @Query("NameQuery") name: String,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null
    ): UserPagedListDto
}