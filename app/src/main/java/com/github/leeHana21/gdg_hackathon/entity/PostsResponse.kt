package com.github.leeHana21.gdg_hackathon.entity

data class PostsResponse(
    val posts : ArrayList<PostDetail>? = null
)

data class PostDetail(
    var title : String ? = null,
    var postId : String ? = null,
    var creatorId : String ? = null,
    var imageUrl : String ? = null,
    var userId : String ? = null
)