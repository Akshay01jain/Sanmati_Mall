package com.sanmati.mall.viewModel

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanmati.mall.commonUtils.SharedPreference
import com.sanmati.mall.connections.APIsCalling
import com.sanmati.mall.connections.ApiEvents
import com.sanmati.mall.connections.KtorClientFactory
import com.sanmati.mall.model.UnitRequest
import com.sanmati.mall.model.UnitResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UnitsViewModel(
) : ViewModel() {

    private val _apiEvents = MutableSharedFlow<ApiEvents>()
    val apiEvents = _apiEvents.asSharedFlow()

    private val _units = MutableStateFlow<List<UnitResponse>>(emptyList())
    val units: StateFlow<List<UnitResponse>> = _units.asStateFlow()

    private val _isLoadingUnits = MutableStateFlow(false)
    val isLoadingUnits: StateFlow<Boolean> = _isLoadingUnits.asStateFlow()


    fun addUnit(unitName: String, unitCode: String, snackbarHostState : SnackbarHostState,onSuccess: (() -> Unit)? = null) {
        viewModelScope.launch {
            val client = KtorClientFactory.build()
            val authApi = APIsCalling(client)
            _apiEvents.emit(ApiEvents.Loading)

            try {
                val response = authApi.addUnit(UnitRequest(unitName, unitCode),
                    SharedPreference.getString("access_token").toString()
                )

                if (response.status in 200..299) {

                    _apiEvents.emit(ApiEvents.Success(response.message))
                    snackbarHostState.showSnackbar(response.message)
                    onSuccess?.invoke()

                } else {
                    _apiEvents.emit(ApiEvents.Error(response.message))
                    snackbarHostState.showSnackbar(response.message)
                }

            } catch (e: Exception) {
                e.printStackTrace()
                _apiEvents.emit(ApiEvents.Error("Network/API Error: ${e.message}"))
                snackbarHostState.showSnackbar("${e.message}")
            }
        }
    }

    //getList
    fun getUnitList(search: String? = null) {
        viewModelScope.launch {
            _isLoadingUnits.value = true
            try {
                val client = KtorClientFactory.build()
                val authApi = APIsCalling(client)
                val accessToken = SharedPreference.getString("access_token").toString()

                val response = authApi.getUnits(search, accessToken)
                if (response.status in 200..299) {
                    _units.value = response.data ?: emptyList()
                    println("${response.data}")
                } else {
                    _apiEvents.emit(ApiEvents.Error(response.message))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _apiEvents.emit(ApiEvents.Error("Network/API Error: ${e.message}"))
            } finally {
                _isLoadingUnits.value = false
            }
        }
    }

    //delete Unit
    fun deleteUnit(unitId: Int, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val client = KtorClientFactory.build()
                val authApi = APIsCalling(client)
                val accessToken = SharedPreference.getString("access_token").toString()

                val response = authApi.deleteUnit(unitId, accessToken)
                if (response.status in 200..299) {
                    onSuccess()
                } else {
                    _apiEvents.emit(ApiEvents.Error(response.message))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _apiEvents.emit(ApiEvents.Error("Network/API Error: ${e.message}"))
            } finally {
                _isLoadingUnits.value = false
            }
        }
    }

}