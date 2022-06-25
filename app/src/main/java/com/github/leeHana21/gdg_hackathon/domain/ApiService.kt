package com.github.leeHana21.gdg_hackathon.domain


import com.github.leeHana21.gdg_hackathon.entity.PostDetailResponse
import com.github.leeHana21.gdg_hackathon.entity.UploadRequest
import com.github.leeHana21.gdg_hackathon.entity.PostsResponse
import com.github.leeHana21.gdg_hackathon.entity.UploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("/api/posts")
    suspend fun getCategoryPosts(@Query("category") category : String) : Response<PostsResponse>

    @GET("/api/posts")
    suspend fun getBookmarkPosts(@Query("postIds") postIds : String) : Response<PostsResponse>

    @GET("/api/posts")
    suspend fun getMyPosts(@Query("creatorId") creatorId : String) : Response<PostsResponse>

    @GET("/api/posts/{postId}")
    suspend fun getPostDetail(@Path("postId") postId : String) : Response<PostDetailResponse>

    @POST("/api/upload")
    @Multipart
    suspend fun postEffect(@Query("userId") userId : String,
                           @Part file : MultipartBody.Part,
                            @Part("data") data : RequestBody) : Response<UploadResponse>
}