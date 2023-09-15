package com.example.bookwithapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookwithapi.di.main.item.BookItem
import com.example.bookwithapi.ui.theme.BookWithApiTheme
import com.example.bookwithapi.ui.theme.HomeViewModel

const val BASE_URL="http://172.20.10.3:8080"



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BookWithApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    HomeScreen()

                }
            }
        }


    }





    @Composable
    fun HomeScreen (){

        val homeViewModel= viewModel(modelClass = HomeViewModel::class.java)
        val books by homeViewModel.books.collectAsState()


        LazyColumn(modifier = Modifier.fillMaxSize()){

            items(books){books : BookItem ->

                BookCard(book=books)


            }
        }

    }

    @Composable
    fun BookCard(book: BookItem) {

        //  val image = rememberImagePainter(data = book.)
        Card(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = book.title)
                Text(text = book.caption)

            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun MyAppPreview() {

        HomeScreen()
    }
}
