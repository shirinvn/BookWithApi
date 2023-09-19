package com.example.bookwithapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookwithapi.di.domain.GetBookUseCase
import com.example.bookwithapi.di.main.item.BookItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject   constructor( val getBookUseCase: GetBookUseCase): ViewModel(){
    private val _books = MutableStateFlow(emptyList<BookItem>())
    val books: StateFlow<List<BookItem>> get()= _books
    init {
        getBooks()
    }

     fun getBooks(){
        viewModelScope.launch {
            try
            {
                val books=getBookUseCase()
                _books.value= books

            }
            catch (_:Exception){}
        }
    }
}


