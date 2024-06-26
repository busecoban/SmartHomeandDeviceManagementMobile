package com.example.cse234_smarthome.data

data class Room(
    val Id: Int,
    val Name: String,
    val HomeId: Int?,
    val Devices: List<Device>?, // List<Device> olarak değiştirildi
)
