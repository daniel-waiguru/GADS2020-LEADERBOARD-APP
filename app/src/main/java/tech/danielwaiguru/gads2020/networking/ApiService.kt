package tech.danielwaiguru.gads2020.networking

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
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
    @POST("/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    suspend fun submitProject(
        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.1824927963") emailAddress: String,
        @Field("entry.284483984") projectLink: String
    ): Response<Void>
}