package com.keyinc.keymono.domain.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class UserDTO(
    @SerializedName("birthDate")
    val birthDate: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("fullName")
    val fullName: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("id")
    val id: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String?,
    @SerializedName("userRole")
    val userRole: String?
)