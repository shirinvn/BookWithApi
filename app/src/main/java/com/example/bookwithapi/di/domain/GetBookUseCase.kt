package com.example.bookwithapi.di.domain

import com.example.bookwithapi.BookRepository
import com.example.bookwithapi.di.main.item.BookItem
import javax.inject.Inject

class GetBookUseCase @Inject constructor( val bookRepository: BookRepository) {


    suspend operator fun invoke():List<BookItem>{


        return bookRepository.getAllBook().shuffled()
    }
}