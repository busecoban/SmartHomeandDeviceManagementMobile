package com.example.cse234_smarthome.data.remote.dto

import com.example.cse234_smarthome.data.Device
import kotlinx.serialization.Serializable
@Serializable
data class GetAllRoomsByHomeIdResponse(
    val id: Int,
    val name : String,
    val homeID : String,
    val devices: List<Device>?,
)
