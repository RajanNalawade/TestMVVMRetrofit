package com.example.testmvvmretrofit.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testmvvmretrofit.model.ApiHelper.GitRepoApi
import com.example.testmvvmretrofit.repository.GithubModelRepository

class RepoItemViewModelFactory(private val repository: GithubModelRepository, private val repoID: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepoItemViewModel(repository, repoID) as T
    }
}