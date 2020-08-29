package tech.danielwaiguru.gads2020.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import tech.danielwaiguru.gads2020.networking.RemoteDataSourceImpl
import tech.danielwaiguru.gads2020.repositories.MainRepository
import tech.danielwaiguru.gads2020.repositories.MainRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSourceImpl): MainRepository =
        MainRepositoryImpl(remoteDataSource)
}