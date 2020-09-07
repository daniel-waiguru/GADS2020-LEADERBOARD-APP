package tech.danielwaiguru.gads2020.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tech.danielwaiguru.gads2020.BuildConfig
private fun clientInstance() =
    OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
    val interceptor = when {
        BuildConfig.DEBUG -> {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
        else -> {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
        }
    }

private fun retrofitInstance(baseUrl: String): Retrofit =
    Retrofit.Builder()
        .client(clientInstance())
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

private fun formServiceInstance(baseUrl: String) =
    retrofitInstance(baseUrl).create(ApiService::class.java)
