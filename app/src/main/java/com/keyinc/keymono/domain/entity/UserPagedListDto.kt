package com.keyinc.keymono.domain.entity

import com.google.gson.annotations.SerializedName


class UserPagedListDto(
    @SerializedName("items")
    val items: List<UserDTO>,
    @SerializedName("pagination")
    val pagination: PageInfo
)


data class PageInfo(
    val size: Int,
    val count: Int,
    val current: Int
)
