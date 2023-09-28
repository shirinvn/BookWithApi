package com.example.bookwithapi

import retrofit2.Response
import retrofit2.http.GET


interface ApiInterface {


    @GET("/book")
    suspend fun getAll(): Response<List<BookResponceModel>>




}