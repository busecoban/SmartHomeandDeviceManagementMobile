package com.example.cse234_smarthome.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequest(
    val FirstName: String,
    val LastName: String,
    val Email: String,
    val UserName: String,
    val Password: String,
    val ConfirmPassword: String
)