package com.example.cse234_smarthome.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
    var Id: String,
    var Name: String,
    var Email: String?,
)