package com.example.testmvvmretrofit.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import com.example.testmvvmretrofit.MainActivity
import com.example.testmvvmretrofit.databinding.FragmentGithubRepoListBinding
import com.example.testmvvmretrofit.model.GithubReposItem
import com.example.testmvvmretrofit.view_models.GithubRepoViewModel
import com.example.testmvvmretrofit.views.adapters.GithubRepoAdapter
import com.example.testmvvmretrofit.views.callbacks.OnGithubRepoClick
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentGithubRepoList.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class FragmentGithubRepoList : Fragment(), OnGithubRepoClick {

    private val githubRepoViewModel: GithubRepoViewModel by viewModels<GithubRepoViewModel>()

    @Inject
    lateinit var listAdapter: GithubRepoAdapter
    private lateinit var binding: FragmentGithubRepoListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGithubRepoListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_github_repo_list, container, false)

        //listAdapter = GithubRepoAdapter(this)
        listAdapter.setonGithubRepoClick(this)
        binding.projectList.adapter = listAdapter
        binding.isLoading = true

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val gitRepoApi = RetrofitHelper.getRetrofitInstance().create(GitRepoApi::class.java)
        //val githubModelRepository = GithubModelRepository(gitRepoApi)
        /*githubRepoViewModel = ViewModelProvider(
            this,
            GithubRepoViewModelFactory(githubModelRepository)
        )[GithubRepoViewModel::class.java]*/

        githubRepoViewModel.lstGithubRepos.observe(viewLifecycleOwner, Observer {
            binding.isLoading = false
            listAdapter.submitList(it)
        })
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onClickGithubRepo(githubReposItem: GithubReposItem) {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            (activity as MainActivity?)?.show(githubReposItem)
        }
    }
}