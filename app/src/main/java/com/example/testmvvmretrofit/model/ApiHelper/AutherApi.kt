package com.example.testmvvmretrofit.model.ApiHelper

import com.example.testmvvmretrofit.model.AuthorList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AutherApi {

    @GET("/authors")
    suspend fun getAutherList(@Query("sortBy") sortBy:String): Response<AuthorList>
}