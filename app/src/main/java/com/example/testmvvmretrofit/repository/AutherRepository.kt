package com.example.testmvvmretrofit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testmvvmretrofit.model.ApiHelper.AutherApi
import com.example.testmvvmretrofit.model.AuthorList

class AutherRepository(private val autherApi: AutherApi) {
    private val autherList = MutableLiveData<AuthorList>()

    val mAutherList : LiveData<AuthorList>
        get() = autherList

    suspend fun getAuthorList(sortBy: String) {
        val result  = autherApi.getAutherList(sortBy)
        if (result.body() != null){
            autherList.postValue(result.body())
        }
    }
}