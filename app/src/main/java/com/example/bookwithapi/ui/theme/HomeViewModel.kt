package com.example.bookwithapi.ui.theme

import androidx.compose.runtime.MutableState
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
class HomeViewModel @Inject constructor(private val getBookUseCase: GetBookUseCase): ViewModel(){
    private val _books = MutableStateFlow(emptyList<BookItem>())
    val books: StateFlow<List<BookItem>> get()= _books
    init {
        getBooks()
    }

    private fun getBooks(){
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