package tech.danielwaiguru.gads2020.repositories

import tech.danielwaiguru.gads2020.models.LearningLeader
import tech.danielwaiguru.gads2020.models.Resource
import tech.danielwaiguru.gads2020.models.SkillIQLeader
import tech.danielwaiguru.gads2020.networking.RemoteDataSource
import javax.inject.Inject

class MainRepositoryImpl
@Inject constructor(private val remoteDataSource: RemoteDataSource): MainRepository {
    override suspend fun fetchLearningLeaders(): Resource<List<LearningLeader>> =
        remoteDataSource.getTopLearningLeaders()

    override suspend fun fetchIQLeaders(): Resource<List<SkillIQLeader>> =
        remoteDataSource.getTopIQLeaders()

    override suspend fun submitProject(
        firstName: String, lastName: String, email: String, projectLink: String
    ): Resource<Void> =
        remoteDataSource.submitProject(firstName, lastName, email, projectLink)
}