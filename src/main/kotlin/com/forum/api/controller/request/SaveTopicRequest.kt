package com.forum.api.controller.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class SaveTopicRequest(
    @field:NotEmpty(message = "Título deve ser preenchido") val title: String,
    @field:NotEmpty(message = "Mensagem deve ser preenchida") val message: String,
    @field:NotNull val courseId: Long,
    @field:NotNull val userId: Long
)
