package tech.danielwaiguru.gads2020.networking

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import tech.danielwaiguru.gads2020.models.LearningLeader
import tech.danielwaiguru.gads2020.models.SkillIQLeader

/**
 * Retrofit powered api calls
 */
private const val SUBMIT_URL = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse"
interface ApiService {
    @GET("/api/hours")
    suspend fun getTopLearningLeaders(): List<LearningLeader>
    @GET("/api/skilliq")
    suspend fun getTopIQLeaders(): Response<List<SkillIQLeader>>

    @POST
    @FormUrlEncoded
    suspend fun submitProject(
        @Url url: String = SUBMIT_URL,
        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.1824927963") emailAddress: String,
        @Field("entry.284483984") projectLink: String
    ): Response<Void>
}