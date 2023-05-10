package com.example.testmvvmretrofit.model.ApiHelper

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val GIT_HUB_BASE_URL  = "https://api.github.com/"

    fun getRetrofitInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(GIT_HUB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}