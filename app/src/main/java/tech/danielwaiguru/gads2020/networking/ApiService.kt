package tech.danielwaiguru.gads2020.networking

import retrofit2.http.GET
import tech.danielwaiguru.gads2020.models.response.LearningLeaderResponse
import tech.danielwaiguru.gads2020.models.response.SkillIQLeaderResponse

/**
 * Retrofit powered api calls
 */
interface ApiService {
    @GET("/api/hours")
    suspend fun getTopLearningLeaders(): LearningLeaderResponse
    @GET("/api/skilliq")
    suspend fun getTopIQLeaders(): SkillIQLeaderResponse
}