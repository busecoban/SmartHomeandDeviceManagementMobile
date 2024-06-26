package com.example.cse234_smarthome.data.remote

import com.example.cse234_smarthome.data.remote.dto.ApiResponse
import com.example.cse234_smarthome.data.remote.dto.AuthRequest
import com.example.cse234_smarthome.data.remote.dto.UserData
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*

interface AuthFunction {
    suspend fun authenticate(authRequest: AuthRequest): ApiResponse<UserData>?

    companion object {
        fun create(): AuthFunction {
            return AuthFunctionImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(ContentNegotiation) {
                        json(kotlinx.serialization.json.Json {
                            prettyPrint = true
                            isLenient = true
                            ignoreUnknownKeys = true
                        })
                    }
                }
            )
        }
    }
}