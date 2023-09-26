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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
        LazyVerticalGrid(columns = GridCells.Adaptive(110.dp)
            , modifier = Modifier.fillMaxSize(),
            content = {
                items(bookList){book->

                BoxCard(image = R.drawable.ic_launcher_foreground, title ="salam" )
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
            })
    }



    @Preview
    @Composable
    fun HomeView(){

        BoxCard(image = R.drawable.ic_launcher_foreground, title = "اسم کتاب")

    }

}

