package com.example.testmvvmretrofit.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testmvvmretrofit.databinding.FragmentRepoBinding
import com.example.testmvvmretrofit.model.ApiHelper.GitRepoApi
import com.example.testmvvmretrofit.model.ApiHelper.RetrofitHelper
import com.example.testmvvmretrofit.repository.GithubModelRepository
import com.example.testmvvmretrofit.view_models.RepoItemViewModel
import com.example.testmvvmretrofit.view_models.RepoItemViewModelFactory

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentRepo.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentRepo : Fragment() {

    private val KEY_PROJECT_ID = "project_id"
    lateinit var binding: FragmentRepoBinding
    lateinit var viewModel: RepoItemViewModel

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
        val api = RetrofitHelper.getRetrofitInstance().create(GitRepoApi::class.java)
        val repository = GithubModelRepository(api)
        viewModel = ViewModelProvider(
            this,
            RepoItemViewModelFactory(repository, arguments?.getString(KEY_PROJECT_ID).toString())
        )[RepoItemViewModel::class.java]

        binding.isLoading = true

        viewModel.repoItem.observe(viewLifecycleOwner, Observer {
            binding.isLoading = false
            binding.repoItem = viewModel
        })
    }

    fun forRepo(repoID : String) : FragmentRepo{
        val frag = FragmentRepo()
        val args = Bundle()
        args.putString(KEY_PROJECT_ID, repoID)
        frag.arguments = args

        return frag
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentRepo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentRepo().apply {

            }
    }
}