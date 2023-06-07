package com.example.testmvvmretrofit.model.ApiHelper

import com.example.testmvvmretrofit.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofitInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.GIT_HUB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}