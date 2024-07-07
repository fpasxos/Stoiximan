package com.fps.events.data.models

import kotlinx.serialization.Serializable

@Serializable
data class SportCategoryDto(
    val i: String? = "",
    val d: String? = "",
    val e: List<SportEventItemDto>? = emptyList()
)