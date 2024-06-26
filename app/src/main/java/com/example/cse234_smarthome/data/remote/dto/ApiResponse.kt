package com.example.cse234_smarthome.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val succeeded: Boolean,
    val message: String,
    val errors: List<String>?,
    val data: T
)
