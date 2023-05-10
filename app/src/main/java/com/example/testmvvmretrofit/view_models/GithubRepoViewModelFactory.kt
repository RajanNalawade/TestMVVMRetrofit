package com.example.testmvvmretrofit.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testmvvmretrofit.repository.GithubModelRepository

class GithubRepoViewModelFactory(private val githubModelRepository: GithubModelRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GithubRepoViewModel(githubModelRepository) as T
    }
}