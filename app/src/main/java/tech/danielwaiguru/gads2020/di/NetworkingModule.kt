package tech.danielwaiguru.gads2020.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tech.danielwaiguru.gads2020.networking.ApiService
import tech.danielwaiguru.gads2020.networking.RemoteDataSource
import tech.danielwaiguru.gads2020.networking.RemoteDataSourceImpl
import javax.inject.Singleton
@Module
@InstallIn(ApplicationComponent::class)
object NetworkingModule {
    private const val BASE_URL = "https://gadsapi.herokuapp.com"
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    @Provides
    fun provideMoshi(): Converter.Factory = MoshiConverterFactory.create()
    @Singleton
    @Provides
    fun provideRetrofitBuilder(client: OkHttpClient, factory: Converter.Factory): Retrofit{
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(factory)
            .build()
    }
    @Singleton
    @Provides
    fun apiServiceBuilder(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    /*@Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiService) =
        RemoteDataSourceImpl(apiService)*/
}