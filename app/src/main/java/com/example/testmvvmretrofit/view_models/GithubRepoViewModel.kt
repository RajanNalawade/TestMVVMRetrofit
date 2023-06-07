package com.example.testmvvmretrofit.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmvvmretrofit.repository.GithubModelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubRepoViewModel @Inject constructor(private val githubModelRepository: GithubModelRepository) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO){
            githubModelRepository.getProjectLists(user_name = "RajanNalawade")
        }
    }

    val lstGithubRepos = githubModelRepository.mLstGithubRepos
}