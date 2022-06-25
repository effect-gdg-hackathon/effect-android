package com.github.leeHana21.gdg_hackathon.domain


class ApiRepository {
    suspend fun getCategoryPosts(category : String) =
        ApiClient.getInstance().getCategoryPosts(category)

    suspend fun getBookMarkPosts(postIds : String) =
        ApiClient.getInstance().getBookmarkPosts(postIds)

    suspend fun getMyPosts(creatorId : String) =
        ApiClient.getInstance().getMyPosts(creatorId)

    suspend fun getPostDetail(postId : String) =
        ApiClient.getInstance().getPostDetail(postId)

    suspend fun postEffect(userId : String) =
        ApiClient.getInstance().postEffect(userId)
}