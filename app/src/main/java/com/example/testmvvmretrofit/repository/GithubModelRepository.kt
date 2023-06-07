package com.example.testmvvmretrofit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testmvvmretrofit.model.ApiHelper.GitRepoApi
import com.example.testmvvmretrofit.model.GithubRepos
import com.example.testmvvmretrofit.model.GithubReposItem
import javax.inject.Inject

class GithubModelRepository @Inject constructor(private val gitRepoApi: GitRepoApi) {
    private val lstGithubRepos = MutableLiveData<GithubRepos>()
    val mLstGithubRepos : LiveData<GithubRepos>
        get() = lstGithubRepos

    private val gitHubRepoItem = MutableLiveData<GithubReposItem>()
    val mGitHubRepoItem : LiveData<GithubReposItem>
        get() = gitHubRepoItem

    suspend fun getProjectLists(user_name : String){
        val result = gitRepoApi.getProjectLists(user_name)
        if (result.body() != null){
            lstGithubRepos.postValue(result.body())
        }
    }

    suspend fun getProjectDetails(user_name : String, repo_name: String){
        val result = gitRepoApi.getProjectDetails(user_name, repo_name)
        if (result.body() != null){
            gitHubRepoItem.postValue(result.body())
        }
    }

}