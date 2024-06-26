package com.example.cse234_smarthome.data

data class Device(
    val id: Int,
    val name: String,
    val type: String,
    val status: Boolean,
    val roomId: Int
)
