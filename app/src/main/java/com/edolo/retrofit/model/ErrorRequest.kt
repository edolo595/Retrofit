package com.edolo.retrofit.model

data class ErrorRequest(
    val message: List<String>?,
    val error: String = "",
    val statusCode: Int = 0
)

data class ErrorRequest2(
    val message: String?,
    val error: String = "",
    val statusCode: Int = 0
)