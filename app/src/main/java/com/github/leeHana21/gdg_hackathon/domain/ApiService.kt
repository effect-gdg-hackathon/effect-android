package com.github.leeHana21.gdg_hackathon.domain


import com.github.leeHana21.gdg_hackathon.entity.MainRequest
import com.github.leeHana21.gdg_hackathon.entity.MainResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface ApiService {
    @GET("/api/api")
    fun getMain( @Body mainRequest: MainRequest) : Response<MainResponse>
}