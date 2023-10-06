package com.example.bookwithapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {


    @GET("/book")
    suspend fun getAll(): Response<List<BookResponceModel>>

    @GET("/book{title}")
    suspend fun getCaptionItem( @Query("title") title:String): Response<BookResponceModel>



}