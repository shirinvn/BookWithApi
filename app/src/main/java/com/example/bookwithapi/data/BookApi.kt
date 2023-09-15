package com.example.bookwithapi.data

import com.example.bookwithapi.data.model.Book
import com.example.bookwithapi.util.Constants.Companion.BOOK_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface BookApi{


    @GET(BOOK_ENDPOINT)
    suspend fun getAll():Response<List<Book>>


}