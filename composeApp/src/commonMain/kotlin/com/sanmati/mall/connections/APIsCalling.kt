package com.sanmati.mall.connections

import com.sanmati.mall.commonUtils.Constant
import com.sanmati.mall.model.ApiResponse
import com.sanmati.mall.model.LoginRequest
import com.sanmati.mall.model.LoginResponse
import com.sanmati.mall.model.UnitRequest
import com.sanmati.mall.model.UnitResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class APIsCalling (private val client : HttpClient)
{
    //login
    suspend fun login(request: LoginRequest): ApiResponse<LoginResponse> {

        return client.post(Constant.USER_LOGIN) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    /*
    * UNITS
    * */
    //GET Unit List
    suspend fun getUnits(search: String? = null, accessToken: String): ApiResponse<List<UnitResponse>> {
        val url = buildString {
            append(Constant.UNITS)
            if (!search.isNullOrEmpty()) append("?search=$search")
        }

        return client.get(url) {
            header("Authorization", "Bearer $accessToken")
            accept(ContentType.Application.Json)
        }.body()


    }

    //Add Unit
    suspend fun addUnit(request: UnitRequest, accessToken : String): ApiResponse<String> {
        return client.post(Constant.UNITS) {
            header("Authorization", "Bearer $accessToken")
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    //Delete Unit
    suspend fun deleteUnit(unitId: Int?, accessToken: String): ApiResponse<String> {
        val url = "${Constant.UNITS_DELETE}?unit_id=$unitId"
        return client.delete(url) {
            header("Authorization", "Bearer $accessToken")
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }.body()
    }


    /*
    * CATEGORIES
    * */
    //GET Categories List
    suspend fun getCategories(search: String? = null, accessToken: String): ApiResponse<List<UnitResponse>> {
        val url = buildString {
            append(Constant.CATEGORIES)
            if (!search.isNullOrEmpty()) append("?search=$search")
        }

        return client.get(url) {
            header("Authorization", "Bearer $accessToken")
            accept(ContentType.Application.Json)
        }.body()


    }

    //Add Unit
    suspend fun addCategories(request: UnitRequest, accessToken : String): ApiResponse<String> {
        return client.post(Constant.CATEGORIES) {
            header("Authorization", "Bearer $accessToken")
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }






}