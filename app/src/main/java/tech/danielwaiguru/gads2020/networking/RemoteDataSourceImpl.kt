package tech.danielwaiguru.gads2020.networking

import tech.danielwaiguru.gads2020.models.LearningLeader
import tech.danielwaiguru.gads2020.models.Resource
import tech.danielwaiguru.gads2020.models.SkillIQLeader
import timber.log.Timber
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService): RemoteDataSource {
    override suspend fun getTopLearningLeaders(): Resource<List<LearningLeader>> =
        try {
            val result = apiService.getTopLearningLeaders()
           Resource.Success(result)
        }
        catch (error: Throwable){
            Timber.d(error.message.toString())
            Resource.Error("Server error encountered!")
        }

    override suspend fun getTopIQLeaders(): Resource<List<SkillIQLeader>> =
        try {
            val result = apiService.getTopIQLeaders()
            Resource.Success(result.body()!!)
        }
        catch (error: Throwable){
            Timber.d(error.message.toString())
            Resource.Error("Server error encountered!")
        }
}