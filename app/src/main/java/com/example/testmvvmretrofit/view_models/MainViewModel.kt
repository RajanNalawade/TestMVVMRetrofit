package com.example.testmvvmretrofit.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmvvmretrofit.model.AuthorList
import com.example.testmvvmretrofit.repository.AutherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val autherRepository: AutherRepository) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO){
            autherRepository.getAuthorList("name")
        }
    }

    val autherList : LiveData<AuthorList>
        get() = autherRepository.mAutherList

}