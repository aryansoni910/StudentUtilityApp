package com.example.project_1.Data.Network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
//https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=6e687002b3b24ca2a72ac752b6dc845e
interface ApiService {
    @GET("top-headlines")
    suspend fun getNewsFromServer(
        @Query("sources") sources: String = "techcrunch",
        @Query("apiKey") apiKey: String = "6e687002b3b24ca2a72ac752b6dc845e"
    ): Response<StudentModel>
}