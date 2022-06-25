package com.github.leeHana21.gdg_hackathon.view.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.leeHana21.gdg_hackathon.domain.ApiRepository
import com.github.leeHana21.gdg_hackathon.entity.Category
import com.github.leeHana21.gdg_hackathon.entity.PostDetailResponse
import com.github.leeHana21.gdg_hackathon.entity.PostsResponse
import com.github.leeHana21.gdg_hackathon.entity.UploadResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val apiRepository = ApiRepository()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("viewModelException", "exception: $throwable ")
    }

    val postsLiveData = MutableLiveData<PostsResponse>()
    fun getCategoryPosts(category : String) {
        viewModelScope.launch(exceptionHandler) {
            val response = apiRepository.getCategoryPosts(category)
            if(response.isSuccessful) postsLiveData.postValue(response.body())
        }
    }

    val postAllLiveData = MutableLiveData<Triple<PostsResponse,PostsResponse,PostsResponse>>()
    fun getCategoryProjectAll(){
        viewModelScope.launch(exceptionHandler) {
            val popularRs = apiRepository.getCategoryPosts(Category.POPULAR.categoryName)
            val lifeRs = apiRepository.getCategoryPosts(Category.LIFE.categoryName)
            val interviewRs = apiRepository.getCategoryPosts(Category.INTERVIEW.categoryName)
            if(popularRs.isSuccessful && lifeRs.isSuccessful && interviewRs.isSuccessful)
                postAllLiveData.postValue(Triple(popularRs.body()!!,lifeRs.body()!!,interviewRs.body()!!))
        }
    }
    val bookMarkPostLiveData = MutableLiveData<PostsResponse>()
    fun getBookMarkPosts(postIds : String) {
        viewModelScope.launch(exceptionHandler) {
            val response = apiRepository.getBookMarkPosts(postIds)
            if(response.isSuccessful) bookMarkPostLiveData.postValue(response.body())
        }
    }
    val myPostPostLiveData = MutableLiveData<PostsResponse>()
    fun getMyPosts(userId : String) {
        viewModelScope.launch(exceptionHandler) {
            val response = apiRepository.getMyPosts(userId)
            if(response.isSuccessful) myPostPostLiveData.postValue(response.body())
        }
    }
    val postDetailLiveData = MutableLiveData<PostDetailResponse>()
    fun getPostDetail(postId : String) {
        viewModelScope.launch(exceptionHandler) {
            val response = apiRepository.getPostDetail(postId)
            if(response.isSuccessful) postDetailLiveData.postValue(response.body())
        }
    }

    val uploadResponse = MutableLiveData<UploadResponse>()
    fun postEffect(userId : String) {
        viewModelScope.launch(exceptionHandler) {
            val response = apiRepository.postEffect(userId)
            if(response.isSuccessful) uploadResponse.postValue(response.body())

        }
    }
}