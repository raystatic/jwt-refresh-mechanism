package com.example.jwtrefreshdemo

data class ResponseModel(
    val message: String? = null,
    val token: String? = null,
    val data: String? = null,
    val isLoading: Boolean = false
)
