package com.example.testmvvmretrofit.views.callbacks

import com.example.testmvvmretrofit.model.GithubReposItem

interface OnGithubRepoClick {
    fun onClickGithubRepo(githubReposItem: GithubReposItem)
}