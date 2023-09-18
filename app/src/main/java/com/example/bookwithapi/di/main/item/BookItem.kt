package com.example.bookwithapi.di.main.item

import com.example.bookwithapi.data.model.Book

data class BookItem(

val title: String,
val caption: String
)

fun Book.toBookItem()= BookItem(title,caption)