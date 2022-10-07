package com.forum.api.controller.response

import java.time.LocalDateTime

data class ErrorResponse(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val statusCode: Int,
    val error: String,
    val message: String?,
    val path: String
)
