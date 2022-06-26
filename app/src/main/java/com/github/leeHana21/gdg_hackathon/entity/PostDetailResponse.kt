package com.github.leeHana21.gdg_hackathon.entity

data class PostDetailResponse(
    var category: String? = null,
    var title : String? = null,
    var content : String? = null,
    var postid : String? = null,
    var creatorid : String? = null,
    var imageUrl : String? = null,
    var userId : String? = null
)