package com.example.testmvvmretrofit.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmvvmretrofit.repository.GithubModelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GithubRepoViewModel(private val githubModelRepository: GithubModelRepository) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO){
            githubModelRepository.getProjectLists(user_name = "RajanNalawade")
        }
    }

    val lstGithubRepos = githubModelRepository.mLstGithubRepos
}