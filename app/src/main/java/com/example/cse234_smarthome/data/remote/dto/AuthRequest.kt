package com.example.cse234_smarthome.data.remote.dto

import kotlinx.serialization.Serializable
@Serializable
data class AuthRequest(
    val Email: String,
    val Password: String
)




