package com.example.cse234_smarthome.data

object UserManager {
    private var currentUser: User? = null

    fun loginUser(user: User) {
        currentUser = user
    }

    fun logoutUser() {
        currentUser = null
    }

    fun getCurrentUser(): User? {
        return currentUser
    }

    fun isLoggedIn(): Boolean {
        return currentUser != null
    }
}
