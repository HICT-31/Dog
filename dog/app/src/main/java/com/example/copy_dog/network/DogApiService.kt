package com.example.copy_dog.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://dog.ceo/api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface DogApiService {
    @GET("breeds/image/random/10")
    suspend fun getPhotos(): DogImage
}

object DogApi {
    val retrofitService: DogApiService by lazy {
        retrofit.create(DogApiService::class.java)
    }
}