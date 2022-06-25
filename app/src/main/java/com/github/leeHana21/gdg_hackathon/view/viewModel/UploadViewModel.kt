package com.github.leeHana21.gdg_hackathon.view.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.leeHana21.gdg_hackathon.domain.ApiRepository
import com.github.leeHana21.gdg_hackathon.entity.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import java.lang.Exception

class UploadViewModel: ViewModel() {
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

    val postAllLiveData = MutableLiveData<Triple<PostsResponse, PostsResponse, PostsResponse>>()
    fun getCategoryProjectAll(){
        viewModelScope.launch(exceptionHandler) {
            try {
                val popularRs = apiRepository.getCategoryPosts(Category.POPULAR.categoryName)
                val lifeRs = apiRepository.getCategoryPosts(Category.LIFE.categoryName)
                val interviewRs = apiRepository.getCategoryPosts(Category.INTERVIEW.categoryName)
                if(popularRs.isSuccessful && lifeRs.isSuccessful && interviewRs.isSuccessful){
                    PostsData.popularData = popularRs.body()!!
                    PostsData.lifeData = lifeRs.body()!!
                    PostsData.interviewData = interviewRs.body()!!
                    postAllLiveData.postValue(Triple(popularRs.body()!!,lifeRs.body()!!,interviewRs.body()!!))
                }
            } catch (e : Exception) {
                e.printStackTrace()
            }
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
    fun postEffect(userId : String, file : MultipartBody.Part, requestBody: RequestBody) {
        viewModelScope.launch(exceptionHandler) {
            val response = apiRepository.postEffect(userId, file,requestBody)
            if(response.isSuccessful) uploadResponse.postValue(response.body())
        }
    }
}