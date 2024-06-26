package com.example.cse234_smarthome.data.remote

import com.example.cse234_smarthome.data.remote.dto.AddRoomRequest
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json


class RoomFunctionImpl(private val client: HttpClient) :   RoomFunction {
    override suspend fun addRoom(addRoomRequest: AddRoomRequest): Int {
        return try {
            val url =
                "https://softwarebackenddeployment2.azurewebsites.net/api/v1/Room/AddRoom"
            val response: HttpResponse = client.post(url) {
                url {
                    parameters.append("name", addRoomRequest.name)
                    parameters.append("HomeId", "${addRoomRequest.HomeId}")
                }
                contentType(ContentType.Application.Json)
            }
            if (response.status == HttpStatusCode.OK) {
                val responseBody: String = response.bodyAsText()
                val roomId = Json.decodeFromString<Int>(responseBody)
                roomId
            } else {
                -1
            }
        } catch (e: Exception) {
            e.printStackTrace()
            -5
        }

    }
}