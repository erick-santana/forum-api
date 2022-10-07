package com.forum.api.controller.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class UpdateTopicRequest(
    @field:NotNull val id: Long,
    @field:NotEmpty val title: String,
    @field:NotEmpty val message: String
)
