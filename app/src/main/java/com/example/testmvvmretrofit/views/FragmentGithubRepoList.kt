package com.example.testmvvmretrofit.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testmvvmretrofit.MainActivity
import com.example.testmvvmretrofit.databinding.FragmentGithubRepoListBinding
import com.example.testmvvmretrofit.model.ApiHelper.GitRepoApi
import com.example.testmvvmretrofit.model.ApiHelper.RetrofitHelper
import com.example.testmvvmretrofit.model.GithubReposItem
import com.example.testmvvmretrofit.repository.GithubModelRepository
import com.example.testmvvmretrofit.view_models.GithubRepoViewModel
import com.example.testmvvmretrofit.view_models.GithubRepoViewModelFactory
import com.example.testmvvmretrofit.views.adapters.GithubRepoAdapter
import com.example.testmvvmretrofit.views.callbacks.OnGithubRepoClick


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentGithubRepoList.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentGithubRepoList : Fragment(), OnGithubRepoClick {

    private lateinit var githubRepoViewModel: GithubRepoViewModel

    private lateinit var listAdapter: GithubRepoAdapter
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

        listAdapter = GithubRepoAdapter(this)
        binding.projectList.adapter = listAdapter
        binding.isLoading = true



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gitRepoApi = RetrofitHelper.getRetrofitInstance().create(GitRepoApi::class.java)
        val githubModelRepository = GithubModelRepository(gitRepoApi)
        githubRepoViewModel = ViewModelProvider(this, GithubRepoViewModelFactory(githubModelRepository))[GithubRepoViewModel::class.java]

        githubRepoViewModel.lstGithubRepos.observe(viewLifecycleOwner, Observer {
            binding.isLoading = false
            listAdapter.submitList(it)
        })
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentGithubRepoList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentGithubRepoList().apply {

            }
    }

    override fun onClickGithubRepo(githubReposItem: GithubReposItem) {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)){
            (activity as MainActivity?)?.show(githubReposItem)
        }
    }
}