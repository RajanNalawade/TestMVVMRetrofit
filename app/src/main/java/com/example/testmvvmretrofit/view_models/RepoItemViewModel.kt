package com.example.testmvvmretrofit.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.testmvvmretrofit.repository.GithubModelRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RepoItemViewModel @AssistedInject constructor(
    private val githubModelRepository: GithubModelRepository,
    @Assisted
    private val repoID: String
) :
    ViewModel() {

    init {
        Log.d("Repo ID", "$repoID")
        //if (id.isNotEmpty()) {
        viewModelScope.launch(Dispatchers.IO) {
            githubModelRepository
                .getProjectDetails("RajanNalawade", repoID)
        }
        //}
    }

    val repoItem = githubModelRepository.mGitHubRepoItem

    @AssistedFactory
    interface Factory {
        fun createRepoID(id: String) : RepoItemViewModel

        companion object {
            fun provideRepoItemViewModelFactory(
                factory: Factory,
                id: String
            ): ViewModelProvider.Factory {
                return object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return factory.createRepoID(id) as T
                    }
                }
            }
        }
    }
}