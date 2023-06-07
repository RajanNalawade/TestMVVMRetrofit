package com.example.testmvvmretrofit.di

import com.example.testmvvmretrofit.model.ApiHelper.GitRepoApi
import com.example.testmvvmretrofit.model.GithubReposItem
import com.example.testmvvmretrofit.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object GitRepoApiModule {

    @Singleton
    @Provides
    fun provideGitRepoApi(retrofit: Retrofit) : GitRepoApi{
        return retrofit.create(GitRepoApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.GIT_HUB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}