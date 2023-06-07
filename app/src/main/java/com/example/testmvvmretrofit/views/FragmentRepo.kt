package com.example.testmvvmretrofit.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.testmvvmretrofit.databinding.FragmentRepoBinding
import com.example.testmvvmretrofit.view_models.RepoItemViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentRepo.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class FragmentRepo : Fragment() {

    private val KEY_PROJECT_ID = "project_id"
    lateinit var binding: FragmentRepoBinding

    @Inject
    lateinit var factory: RepoItemViewModel.Factory

    private val viewModel: RepoItemViewModel by viewModels {
        RepoItemViewModel.Factory.provideRepoItemViewModelFactory(
            factory,
            arguments?.getString(KEY_PROJECT_ID).toString()
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepoBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_repo, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /*val api = RetrofitHelper.getRetrofitInstance().create(GitRepoApi::class.java)
        val repository = GithubModelRepository(api)
        viewModel = ViewModelProvider(
            this,
            RepoItemViewModelFactory(repository, arguments?.getString(KEY_PROJECT_ID).toString())
        )[RepoItemViewModel::class.java]*/

        /*val viewModel: RepoItemViewModel by viewModels<RepoItemViewModel> {
            RepoItemViewModelFactory.provideRepoItemViewModelFactory(factory, arguments?.getString(KEY_PROJECT_ID).toString())
        }*/

        binding.isLoading = true

        viewModel.repoItem.observe(viewLifecycleOwner, Observer {
            binding.isLoading = false
            binding.repoItem = viewModel
        })
    }

    fun forRepo(repoID: String): FragmentRepo {
        val frag = FragmentRepo()
        val args = Bundle()
        args.putString(KEY_PROJECT_ID, repoID)
        frag.arguments = args

        return frag
    }
}