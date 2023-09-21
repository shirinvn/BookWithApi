package com.example.bookwithapi.data.model

import com.example.bookwithapi.data.BookApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class BookService @Inject constructor(retrofit: Retrofit) {

      val bookapi: BookApi = retrofit.create(BookApi::class.java)

    suspend fun getAllBook():List<Book>
    {
        return withContext(Dispatchers.IO){
            val books= bookapi.getAll()
            books.body()?: emptyList()
        }

    }
}
