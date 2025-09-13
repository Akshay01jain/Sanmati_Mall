package com.sanmati.mall.connections

sealed class ApiEvents {
    data class Success(val message: String) : ApiEvents()
    data class Error(val message: String) : ApiEvents()
}
