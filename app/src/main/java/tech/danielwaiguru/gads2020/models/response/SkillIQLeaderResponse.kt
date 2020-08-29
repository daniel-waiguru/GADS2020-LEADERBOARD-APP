package tech.danielwaiguru.gads2020.models.response

import tech.danielwaiguru.gads2020.models.SkillIQLeader

data class SkillIQLeaderResponse (
    val iqLeaders : List<SkillIQLeader> = emptyList()
)