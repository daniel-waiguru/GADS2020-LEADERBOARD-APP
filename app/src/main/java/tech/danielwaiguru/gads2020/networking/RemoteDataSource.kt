package tech.danielwaiguru.gads2020.networking

import tech.danielwaiguru.gads2020.models.response.LearningLeaderResponse
import tech.danielwaiguru.gads2020.models.response.SkillIQLeaderResponse

interface RemoteDataSource {
    fun getTopLearningLeaders(): LearningLeaderResponse
    fun getTopIQLeaders(): SkillIQLeaderResponse
}