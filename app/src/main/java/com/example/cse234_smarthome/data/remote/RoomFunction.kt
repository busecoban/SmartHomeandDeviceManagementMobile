package com.example.cse234_smarthome.data.remote


import com.example.cse234_smarthome.data.remote.dto.AddRoomRequest
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*

interface RoomFunction {

    suspend fun addRoom(addRoomRequest: AddRoomRequest) :Int

    //  suspend fun getAllRoomsByHomeID(homeId : Int): PagedResponse<GetAllRoomsByHomeIdResponse>?
    companion object {
        fun create(): RoomFunction {
            return RoomFunctionImpl(
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