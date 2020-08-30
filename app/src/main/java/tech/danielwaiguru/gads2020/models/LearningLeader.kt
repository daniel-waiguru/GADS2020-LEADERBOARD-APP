package tech.danielwaiguru.gads2020.models

import com.squareup.moshi.Json

data class LearningLeader (
    @field:Json(name = "name") val name: String,
    @field:Json(name = "hours") val hours: Int,
    @field:Json(name = "country") val country: String,
    @field:Json(name = "badgeUrl") val badgeUrl: String
)