package tech.danielwaiguru.gads2020.di

import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

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
        retrofit.create(ApiService::class.jav)
    }
}