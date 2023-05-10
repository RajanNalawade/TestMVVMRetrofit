package com.example.testmvvmretrofit.model.ApiHelper

import com.example.testmvvmretrofit.model.GithubRepos
import com.example.testmvvmretrofit.model.GithubReposItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitRepoApi {

    @GET("/users/{userName}/repos")
    suspend fun getProjectLists(@Path("userName") user_name: String) : Response<GithubRepos>

    @GET("/repos/{userName}/{repoName}")
    suspend fun getProjectDetails(@Path("userName") user_name: String, @Path("repoName") repo_name : String) : Response<GithubReposItem>
}