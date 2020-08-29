package tech.danielwaiguru.gads2020.models.response

import tech.danielwaiguru.gads2020.models.LearningLeader

data class LearningLeaderResponse (
    val learningLeaders: List<LearningLeader> = emptyList()
)