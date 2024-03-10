package com.keyinc.keymono.domain.entity

data class TransferRequestItem(
    val applicantId: String,
    val applicantName: String,
    val key: KeyDto
)
