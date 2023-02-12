package com.example.jwtrefreshdemo

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface TokenApiService {

    @POST("createNewToken")
    fun createNewToken(): Call<ResponseModel>
}