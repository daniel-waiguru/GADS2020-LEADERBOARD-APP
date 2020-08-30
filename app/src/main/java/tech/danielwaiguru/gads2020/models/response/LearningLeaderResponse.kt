package tech.danielwaiguru.gads2020.models.response

import com.squareup.moshi.Json
import tech.danielwaiguru.gads2020.models.LearningLeader

data class LearningLeaderResponse (
    @field:Json(name = "") val learningLeaders: List<LearningLeader> = listOf()
)