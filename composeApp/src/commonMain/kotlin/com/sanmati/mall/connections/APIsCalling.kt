package com.sanmati.mall.connections

import com.sanmati.mall.commonUtils.Constant
import com.sanmati.mall.model.ApiResponse
import com.sanmati.mall.model.LoginRequest
import com.sanmati.mall.model.LoginResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class APIsCalling (private val client : HttpClient)
{
    suspend fun login(request: LoginRequest): ApiResponse<LoginResponse> {

        return client.post(Constant.USER_LOGIN) {
            contentType(ContentType.Application.FormUrlEncoded)
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

}