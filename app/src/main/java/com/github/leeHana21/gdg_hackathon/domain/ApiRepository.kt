package com.github.leeHana21.gdg_hackathon.domain

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.Part


class ApiRepository {
    suspend fun getCategoryPosts(category : String) =
        ApiClient.getInstance().getCategoryPosts(category)

    suspend fun getBookMarkPosts(postIds : String) =
        ApiClient.getInstance().getBookmarkPosts(postIds)

    suspend fun getMyPosts(creatorId : String) =
        ApiClient.getInstance().getMyPosts(creatorId)

    suspend fun getPostDetail(postId : String) =
        ApiClient.getInstance().getPostDetail(postId)

    suspend fun postEffect(userId : String, file : MultipartBody.Part,
                          /* data : MultipartBody.Part*/ requestBody: RequestBody) =
        ApiClient.getInstance().postEffect(userId, file,requestBody)
}