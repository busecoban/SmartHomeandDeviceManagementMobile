package com.example.cse234_smarthome.data.remote.dto

import kotlinx.serialization.Serializable
@Serializable
data class UserData(
    val id: String,
    val userName: String,
    val email: String,
    val roles: List<String>,
    val isVerified: Boolean,
    val jwToken: String
)