package com.sanmati.mall.model

import kotlinx.serialization.Serializable


@Serializable
data class CategoryRequest(
    val name: String
)
@Serializable
data class CategoryResponse(
    val categoryId: Int,
    val name: String,
    val createdAt: String
)
