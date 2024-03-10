package com.keyinc.keymono.domain.entity

data class TransferRequests(
    val items: List<TransferRequestItem>,
    val pagination: PageInfoDto
)
