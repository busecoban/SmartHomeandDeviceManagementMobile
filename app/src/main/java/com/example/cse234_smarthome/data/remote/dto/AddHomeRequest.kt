package com.example.cse234_smarthome.data.remote.dto

import kotlinx.serialization.Serializable
@Serializable
data class AddHomeRequest(
    val name: String,
    val address: String,
    val ownerId: String?,
)


