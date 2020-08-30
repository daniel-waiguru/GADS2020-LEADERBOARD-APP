package tech.danielwaiguru.gads2020.networking

import retrofit2.Response
import retrofit2.http.GET
import tech.danielwaiguru.gads2020.models.LearningLeader
import tech.danielwaiguru.gads2020.models.SkillIQLeader

/**
 * Retrofit powered api calls
 */
interface ApiService {
    @GET("/api/hours")
    suspend fun getTopLearningLeaders(): List<LearningLeader>
    @GET("/api/skilliq")
    suspend fun getTopIQLeaders(): Response<List<SkillIQLeader>>
}