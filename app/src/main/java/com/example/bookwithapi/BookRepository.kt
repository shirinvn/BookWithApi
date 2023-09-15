package com.example.bookwithapi

import com.example.bookwithapi.data.model.Book
import com.example.bookwithapi.data.model.BookService
import com.example.bookwithapi.di.main.item.BookItem
import com.example.bookwithapi.di.main.item.toBookItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BookRepository @Inject constructor( private val bookService: BookService) {



    suspend fun  getAllBook (): List<BookItem>{
        return bookService.getAllBook().map {
            it.toBookItem()
        }
    }


    fun getBooks(callback: (List<Book>?, Throwable?) -> Unit) {
        apiService.getAllBooks().enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                if (response.isSuccessful) {
                    callback(response.body(), null)
                } else {
                    callback(null, Exception("API request failed"))
                }
            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                callback(null, t)
            }
        })
    }
}