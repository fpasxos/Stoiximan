package com.fps.events.data.models

import kotlinx.serialization.Serializable

@Serializable
data class SportEventItemDto(
    val d: String? = "",
    val sh: String? = "",
    val si: String? = "",
    val i: String? = "",
    val tt: Int? = 0
)