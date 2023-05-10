package com.example.testmvvmretrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testmvvmretrofit.model.GithubReposItem
import com.example.testmvvmretrofit.views.FragmentGithubRepoList
import com.example.testmvvmretrofit.views.FragmentRepo


class MainActivity : AppCompatActivity() {

    //lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*val autherApi = RetrofitHelper.getRetrofitInstance().create(AutherApi::class.java)
        val autherRepository = AutherRepository(autherApi)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(autherRepository))[MainViewModel::class.java]
        mainViewModel.autherList.observe(this, Observer {
            Log.d("Auther List", "total count - ${it.count}")
        })*/

        if (savedInstanceState == null) {
            val fragmentGithubRepoList = FragmentGithubRepoList()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragmentGithubRepoList, "Git_List").commit()
        }

    }

    fun show(project: GithubReposItem) {
        val projectFragment = FragmentRepo().forRepo(project.name)
        supportFragmentManager
            .beginTransaction()
            .addToBackStack("project")
            .replace(
                R.id.fragment_container,
                projectFragment, null
            ).commit()
    }
}