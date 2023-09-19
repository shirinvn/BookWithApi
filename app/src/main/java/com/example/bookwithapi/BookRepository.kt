package com.example.bookwithapi

import com.example.bookwithapi.data.model.Book
import com.example.bookwithapi.data.model.BookService
import com.example.bookwithapi.di.main.item.BookItem
import com.example.bookwithapi.di.main.item.toBookItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BookRepository @Inject constructor(  val bookService: BookService) {



    suspend fun  getAllBook (): List<BookItem>{
        return bookService.getAllBook().map {
            it.toBookItem()
        }
    }



}