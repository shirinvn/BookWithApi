package com.example.bookwithapi.data.model

import com.example.bookwithapi.data.BookApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookService @Inject constructor(private val bookapi:BookApi) {

    suspend fun getAllBook():List<Book>
    {
        return withContext(Dispatchers.IO){
            val books= bookapi.getAll()
            books.body()?: emptyList()
        }

    }
}