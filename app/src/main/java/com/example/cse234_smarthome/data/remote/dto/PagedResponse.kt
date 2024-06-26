package com.example.cse234_smarthome.data.remote.dto

import kotlinx.serialization.Serializable
@Serializable
data class PagedResponse<T>(
    val pageNumber: Int,
    val pageSize: Int,
    val succeeded: Boolean,
    val message: String?,
    val errors: List<String>?,
    val data: List<T>?= emptyList()
)
