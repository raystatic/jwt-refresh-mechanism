package com.example.jwtrefreshdemo

import android.content.SharedPreferences
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {

    const val BASE_URL = "http://10.0.2.2:3000/api/"
    const val JWT_TOKEN = "jwt-token"

    private fun getRetrofit(
        sharedPreferences: SharedPreferences
    ): Retrofit {

        val authInterceptor = AuthInterceptor(sharedPreferences = sharedPreferences)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun getApiService(sharedPreferences: SharedPreferences): ApiService
        = getRetrofit(sharedPreferences).create(ApiService::class.java)

    fun getTokenApiService(): TokenApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(TokenApiService::class.java)
    }

}