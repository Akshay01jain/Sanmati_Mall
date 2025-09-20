package com.sanmati.mall.connections

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.sanmati.mall.commonUtils.Constant
import com.sanmati.mall.commonUtils.SharedPreference
import com.sanmati.mall.designUi.ForceLogoutDialog
import com.sanmati.mall.utils.UnauthorizedEventBus
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClientFactory {

    fun build(): HttpClient = HttpClient(provideHttpClientEngine()) {

        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(DefaultRequest) {
            url(Constant.BASE_URL)
            contentType(ContentType.Application.Json)
        }
        install(HttpTimeout) {
            connectTimeoutMillis = 15_000
            socketTimeoutMillis = 15_000
            requestTimeoutMillis = 30_000
        }

        HttpResponseValidator {
            validateResponse { response ->
                val statusCode = response.status.value
                when (statusCode) {
                    in 400..499 -> {
                        if (statusCode == 401) {
                            UnauthorizedEventBus.emitUnauthorized()
                        } else {
                            throw ClientRequestException(response, "Client error")
                        }
                    }

                    in 500..599 -> throw ServerResponseException(response, "Server error")
                }
            }
        }
    }

}

