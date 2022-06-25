package com.github.leeHana21.gdg_hackathon.entity

data class UploadRequest(
    var category : String ? = null,
    var title : String ? = null,
    var content : String ? = null,
    var imageUrl : String ? = null,
    var parentPostId : String ? = null, // 이펙트 퍼뜨리기 시
)