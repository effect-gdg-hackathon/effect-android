package com.github.leeHana21.gdg_hackathon.domain

import com.google.gson.GsonBuilder
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val baseUrl = "https://edda-118-129-228-11.jp.ngrok.io"
    private lateinit var apiService: ApiService
    fun getInstance() : ApiService {
        val builder =
            Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().setLenient().create()))

        val httpClient = OkHttpClient.Builder().addInterceptor(OkHttpProfilerInterceptor())

        val retrofit = builder.client(httpClient.build()).build()

        apiService = retrofit.create(ApiService::class.java)

        return apiService
    }
}