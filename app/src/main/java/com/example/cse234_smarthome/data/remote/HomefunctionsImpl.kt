package com.example.cse234_smarthome.data.remote

import com.example.cse234_smarthome.data.remote.HttpRoutes.Base_url
import com.example.cse234_smarthome.data.remote.dto.AddHomeRequest
import com.example.cse234_smarthome.data.remote.dto.GetHomesByOwnerIDResponse
import com.example.cse234_smarthome.data.remote.dto.PagedResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.call.*
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

class HomeFunctionsImpl (
    private val client : HttpClient
) : HomeFunctions
{
    override suspend fun addHome(addHomeRequest: AddHomeRequest): Int {
        return try {
            val url =
                "https://softwarebackenddeployment2.azurewebsites.net/api/v1/Home/AddHome"
            val response: HttpResponse = client.post(url) {
                url {
                    parameters.append("name", addHomeRequest.name)
                    parameters.append("address", addHomeRequest.address)
                    addHomeRequest.ownerId?.let { parameters.append("ownerID", it) }
                }
                contentType(ContentType.Application.Json)
            }
            if (response.status == HttpStatusCode.OK) {
                val responseBody: String = response.bodyAsText()
                val homeId = Json.decodeFromString<Int>(responseBody)
                homeId
            } else {
                -1
            }
        } catch (e: Exception) {
            e.printStackTrace()
            -5
        }

    }

    override suspend fun getHomesByOwnerID(ownerID: String): PagedResponse<GetHomesByOwnerIDResponse>? {
        return try{
            client.get {
                url("${Base_url}v1/Home/GetAllHomesByOwnerId")
                parameter("PageNumber", 1)
                parameter("PageSize", 10)
                parameter("OwnerId", ownerID)
            }.body()
        } catch(e: Exception) {
            PagedResponse(
                pageNumber = 0,
                pageSize = 0,
                succeeded = false,
                message = "${e.message}",
                errors = listOf("An unknown error occurred."),
                data = emptyList()
            )
        }
    }
}
