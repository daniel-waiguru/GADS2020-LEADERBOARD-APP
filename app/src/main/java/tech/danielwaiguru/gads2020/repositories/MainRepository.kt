package tech.danielwaiguru.gads2020.repositories

import tech.danielwaiguru.gads2020.models.LearningLeader
import tech.danielwaiguru.gads2020.models.Resource
import tech.danielwaiguru.gads2020.models.SkillIQLeader

interface MainRepository {
    suspend fun fetchLearningLeaders(): Resource<List<LearningLeader>>
    suspend fun fetchIQLeaders(): Resource<List<SkillIQLeader>>
}