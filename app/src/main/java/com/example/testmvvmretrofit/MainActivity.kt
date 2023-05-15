package com.example.testmvvmretrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testmvvmretrofit.model.GithubReposItem
import com.example.testmvvmretrofit.views.FragmentGithubRepoList
import com.example.testmvvmretrofit.views.FragmentRepo


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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