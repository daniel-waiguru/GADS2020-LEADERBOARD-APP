package tech.danielwaiguru.gads2020.networking

import tech.danielwaiguru.gads2020.models.LearningLeader
import tech.danielwaiguru.gads2020.models.Resource
import tech.danielwaiguru.gads2020.models.SkillIQLeader

interface RemoteDataSource {
    suspend fun getTopLearningLeaders(): Resource<List<LearningLeader>>
    suspend fun getTopIQLeaders(): Resource<List<SkillIQLeader>>
}