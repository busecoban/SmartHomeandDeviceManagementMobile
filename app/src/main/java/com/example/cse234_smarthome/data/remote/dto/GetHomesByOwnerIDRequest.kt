package com.example.cse234_smarthome.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
//bunu değiştirmem gerekecek
data class GetHomesByOwnerIDRequest(
    val id: Int,
    val name : String,
    val address : String,
    val roomNames: List<String>,
    val ownerId : String
)
