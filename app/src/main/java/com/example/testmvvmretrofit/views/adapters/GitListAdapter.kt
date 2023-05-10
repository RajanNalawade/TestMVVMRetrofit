package com.example.testmvvmretrofit.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testmvvmretrofit.databinding.GithubRepoListItemBinding
import com.example.testmvvmretrofit.model.GithubReposItem
import com.example.testmvvmretrofit.views.callbacks.OnGithubRepoClick

class GitListAdapter(private val onGithubRepoClick: OnGithubRepoClick) :
    ListAdapter<GithubReposItem, GitListAdapter.GitListViewHolder>(GitListDifferentiator()) {

    private lateinit var binding: GithubRepoListItemBinding

    class GitListViewHolder(private val holderBinding: GithubRepoListItemBinding) :
        RecyclerView.ViewHolder(holderBinding.root) {
        fun bind(item: GithubReposItem, listener: OnGithubRepoClick){
            holderBinding.projectData = item
            holderBinding.onCLickGithubRepo = listener
        }
    }

    class GitListDifferentiator : DiffUtil.ItemCallback<GithubReposItem>() {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitListViewHolder {
        this.binding = GithubRepoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GitListViewHolder(this.binding)
    }

    override fun onBindViewHolder(holder: GitListViewHolder, position: Int) {
        val gitRepoItem = getItem(position)

        holder.bind(gitRepoItem, onGithubRepoClick)
    }


}