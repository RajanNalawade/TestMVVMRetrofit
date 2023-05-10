package com.example.testmvvmretrofit.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmvvmretrofit.repository.GithubModelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepoItemViewModel(
    private val githubModelRepository: GithubModelRepository,
    private val repoID: String
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            githubModelRepository
                .getProjectDetails("RajanNalawade", repoID)
        }
    }

    val repoItem = githubModelRepository.mGitHubRepoItem


}