package com.example.cse234_smarthome.data

class Home (
    val  Id : Int,
    val Name : String,
    val Address: String,
    val UserId : Int,
    val Rooms : List<Room>,
    val Users : List<User>,
    val  Url: String?,
) {

}