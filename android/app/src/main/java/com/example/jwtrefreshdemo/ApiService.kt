package com.example.jwtrefreshdemo

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("getSomeData")
    suspend fun getSomeData(): Response<ResponseModel>

}