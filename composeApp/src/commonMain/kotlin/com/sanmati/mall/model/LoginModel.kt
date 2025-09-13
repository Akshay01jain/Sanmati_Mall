package com.sanmati.mall.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val userMobileNumber: String,
    val userPassword: String
)

@Serializable
data class LoginResponse(
    val userId: String,
    val userTypeId: Int,
    val userTypeName: String,
    val username: String,
    val userMobileNumber: String,
    val userActive: Boolean,
    val phoneVerified: Boolean,
    val lastLoginAt: String ?= null,
    val isDeleted: Boolean ?= null,
    val createdAt: String ?= null,
    val updateAt: String ?= null,
    val token: String
)
