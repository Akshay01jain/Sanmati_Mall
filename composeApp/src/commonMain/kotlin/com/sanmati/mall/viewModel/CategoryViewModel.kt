package com.sanmati.mall.viewModel


import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanmati.mall.commonUtils.SharedPreference
import com.sanmati.mall.connections.APIsCalling
import com.sanmati.mall.connections.ApiEvents
import com.sanmati.mall.connections.KtorClientFactory
import com.sanmati.mall.model.CategoryRequest
import com.sanmati.mall.model.CategoryResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private val _apiEvents = MutableSharedFlow<ApiEvents>()
    val apiEvents = _apiEvents.asSharedFlow()

    private val _categories = MutableStateFlow<List<CategoryResponse>>(emptyList())
    val categories: StateFlow<List<CategoryResponse>> = _categories.asStateFlow()

    private val _isLoadingCategories = MutableStateFlow(false)
    val isLoadingCategories: StateFlow<Boolean> = _isLoadingCategories.asStateFlow()


    fun addCategory(name: String, snackbarHostState: SnackbarHostState, onSuccess: (() -> Unit)? = null) {
        viewModelScope.launch {
            val client = KtorClientFactory.build()
            val authApi = APIsCalling(client)
            _apiEvents.emit(ApiEvents.Loading)

            try {
                val response = authApi.addCategory(
                    CategoryRequest(name),
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

    // Get Category List
    fun getCategoryList(search: String? = null) {
        viewModelScope.launch {
            _isLoadingCategories.value = true
            try {
                val client = KtorClientFactory.build()
                val authApi = APIsCalling(client)
                val accessToken = SharedPreference.getString("access_token").toString()

                println("\n $accessToken \n")

                val response = authApi.getCategories(search, accessToken)
                if (response.status in 200..299) {
                    _categories.value = response.data ?: emptyList()
                } else {
                    _apiEvents.emit(ApiEvents.Error(response.message))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _apiEvents.emit(ApiEvents.Error("Network/API Error: ${e.message}"))
            } finally {
                _isLoadingCategories.value = false
            }
        }
    }

    // Delete Category
    fun deleteCategory(categoryId: Int, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val client = KtorClientFactory.build()
                val authApi = APIsCalling(client)
                val accessToken = SharedPreference.getString("access_token").toString()

                val response = authApi.deleteCategory(categoryId, accessToken)
                if (response.status in 200..299) {
                    onSuccess()
                } else {
                    _apiEvents.emit(ApiEvents.Error(response.message))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _apiEvents.emit(ApiEvents.Error("Network/API Error: ${e.message}"))
            } finally {
                _isLoadingCategories.value = false
            }
        }
    }
}
