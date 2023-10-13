package com.example.bookwithapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiService {


    private const val BASE_URL = "http://192.168.1.4:8080"


    val api :ApiInterface by lazy {



        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

    }
}