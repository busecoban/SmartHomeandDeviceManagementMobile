package com.example.cse234_smarthome.data.remote.dto

import kotlinx.serialization.Serializable
@Serializable
data class AddRoomRequest(
    val name: String,
    val HomeId: Int,
)


