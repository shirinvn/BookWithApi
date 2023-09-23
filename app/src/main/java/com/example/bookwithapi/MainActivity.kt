package com.example.bookwithapi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider

import com.example.bookwithapi.ui.theme.BookWithApiTheme





class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BookWithApiTheme {
                MainBookView()
            }
        }
    }



    @Composable
     fun MainBookView() {

        val viewModel = ViewModelProvider(this)[BookViewModel::class.java]
        viewModel.getAllBookSRequest()
        var bookList by remember { mutableStateOf(emptyList<BookResponceModel>()) }


        Column(modifier = Modifier.fillMaxSize()) {
            BookView(bookList = bookList)

        }

        viewModel.bookList.observe(this) {books ->
            bookList=books

        }


        viewModel.postListError.observe(this) {isError ->
            isError?.let{

                Log.e("3636",isError)
            }

        }


        viewModel.loading.observe(this) {isLoading ->


            Log.e("3636",isLoading.toString())


        }
    }


    @Composable
    fun BookView(bookList: List<BookResponceModel>){
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(bookList){ book->
                Column(modifier = Modifier
                    .padding(12.dp)
                    .background(Color.Red)
                    .size(200.dp))
                {
                    Text(text = book.title, color  = Color.White)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = book.caption, color= Color.White)



                }
            }
        }
    }

}

