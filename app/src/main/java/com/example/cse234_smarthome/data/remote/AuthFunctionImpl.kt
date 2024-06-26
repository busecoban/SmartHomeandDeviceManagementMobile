package com.example.cse234_smarthome.data.remote

import com.example.cse234_smarthome.data.remote.dto.ApiResponse
import com.example.cse234_smarthome.data.remote.dto.AuthRequest
import com.example.cse234_smarthome.data.remote.dto.UserData
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json


class AuthFunctionImpl(private val client: HttpClient) : AuthFunction {
    override suspend fun authenticate(authRequest: AuthRequest): ApiResponse<UserData>? {
        return try {
            val url = "http://localhost:5000/api/Account/authenticate"
            val response: HttpResponse = client.post(url) {
                url {
                    parameters.append("Email", authRequest.Email)
                    parameters.append("Password", authRequest.Password)
                }
                contentType(ContentType.Application.Json)
            }
            if (response.status == HttpStatusCode.OK) {
                val responseBody: String = response.bodyAsText()
                Json.decodeFromString<ApiResponse<UserData>>(responseBody)
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
