package com.example.cse234_smarthome.data.remote

import com.example.cse234_smarthome.data.remote.dto.AddHomeRequest
import com.example.cse234_smarthome.data.remote.dto.GetHomesByOwnerIDResponse
import com.example.cse234_smarthome.data.remote.dto.PagedResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

interface HomeFunctions {
    suspend fun addHome(addHomeRequest: AddHomeRequest) : Int
    suspend fun getHomesByOwnerID(ownerID: String): PagedResponse<GetHomesByOwnerIDResponse>?
    companion object {
        fun create(): HomeFunctions {
            return HomeFunctionsImpl(
                client =  HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(ContentNegotiation) {
                        json(kotlinx.serialization.json.Json { ignoreUnknownKeys = true })
                    }
                }
            )
        }
    }
}