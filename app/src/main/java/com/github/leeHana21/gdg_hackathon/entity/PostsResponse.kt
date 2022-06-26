package com.github.leeHana21.gdg_hackathon.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class PostsResponse(
    @SerializedName("list")
    val posts : ArrayList<PostDetail>? = null
) : Parcelable

@Parcelize
data class PostDetail(
    var category: String? = null,
    var title : String ? = null,
    var postId : String ? = null,
    var content : String? = null,
    var creatorId : String ? = null,
    var imageUrl : String ? = null,
    var userId : String ? = null
) : Parcelable