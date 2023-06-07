package com.example.testmvvmretrofit.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testmvvmretrofit.databinding.GithubRepoListItemBinding
import com.example.testmvvmretrofit.model.GithubReposItem
import com.example.testmvvmretrofit.views.callbacks.OnGithubRepoClick
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

class GithubRepoAdapter @Inject constructor() :
    ListAdapter<GithubReposItem, GithubRepoAdapter.GithubRepoViewHolder>(ListDifferentiator()) {

    private var onGithubRepoClick: OnGithubRepoClick? = null
    fun setonGithubRepoClick(mListener : OnGithubRepoClick){
        this.onGithubRepoClick = mListener
    }

    private lateinit var githubRepoListItemBinding: GithubRepoListItemBinding

    class GithubRepoViewHolder(private val githubRepoListItemBinding: GithubRepoListItemBinding) :
        RecyclerView.ViewHolder(githubRepoListItemBinding.root) {
        fun bind(githubReposItem: GithubReposItem, onGithubRepoClick: OnGithubRepoClick) {
            githubRepoListItemBinding.projectData = githubReposItem
            githubRepoListItemBinding.onCLickGithubRepo = onGithubRepoClick
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepoViewHolder {
        this.githubRepoListItemBinding =
            GithubRepoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GithubRepoViewHolder(this.githubRepoListItemBinding)
    }

    override fun onBindViewHolder(holder: GithubRepoViewHolder, position: Int) {
        val repo = getItem(position)
        holder.bind(repo, onGithubRepoClick!!)
    }

    class ListDifferentiator : DiffUtil.ItemCallback<GithubReposItem>() {
        override fun areItemsTheSame(oldItem: GithubReposItem, newItem: GithubReposItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GithubReposItem,
            newItem: GithubReposItem
        ): Boolean {
            return oldItem == newItem
        }

    }
}