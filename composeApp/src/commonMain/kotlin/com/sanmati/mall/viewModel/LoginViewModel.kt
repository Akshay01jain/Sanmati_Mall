package com.sanmati.mall.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanmati.mall.commonUtils.SharedPreference
import com.sanmati.mall.connections.APIsCalling
import com.sanmati.mall.connections.ApiEvents
import com.sanmati.mall.connections.KtorClientFactory
import com.sanmati.mall.model.LoginRequest
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _apiEvents = MutableSharedFlow<ApiEvents>()
    val apiEvents = _apiEvents.asSharedFlow()

    fun performLogin(userMobileNumber: String, userPassword: String) {
        viewModelScope.launch {
            val client = KtorClientFactory.build()
            val authApi = APIsCalling(client)

            try {
                val response = authApi.login(LoginRequest(userMobileNumber, userPassword))

                if (response.status == 200) {
                    SharedPreference.putString("access_token", response.data?.token.toString())
                    SharedPreference.putString("user_type", response.data?.userTypeName.toString().lowercase().trim())
                    println("API Usertype: ${response.data?.userTypeName}")
                    _apiEvents.emit(ApiEvents.Success("Login successful"))
                } else {
                    println("API Error: ${response.message}")
                    _apiEvents.emit(ApiEvents.Error(response.message))
                }
            } catch (e: Exception) {
                _apiEvents.emit(ApiEvents.Error("API Error: ${e.message}"))
            }
        }
    }
}
