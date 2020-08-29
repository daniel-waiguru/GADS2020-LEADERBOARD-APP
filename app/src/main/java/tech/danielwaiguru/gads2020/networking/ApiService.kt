package tech.danielwaiguru.gads2020.networking

import retrofit2.http.GET

/**
 * Retrofit powered api calls
 */
interface ApiService {
    @GET("/api/hours")
    fun getTopLearningLeaders(){

    }
    @GET("/api/skilliq")
    fun getTopIQLeaders(){
        
    }
}