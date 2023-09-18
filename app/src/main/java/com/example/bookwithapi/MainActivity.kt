package com.example.bookwithapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookwithapi.di.main.item.BookItem
import com.example.bookwithapi.ui.theme.BookWithApiTheme

const val BASE_URL="http://127.0.0.1:8080"



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BookWithApiTheme {
                    HomeScreen()

            }
        }


    }

    @Composable
    fun HomeScreen() {

        val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
        val books by homeViewModel.books.collectAsState()


        LazyColumn(modifier = Modifier.fillMaxSize()) {

            items(books) { books: BookItem ->

                BookCard(book = books)


            }
        }

    }

    @Composable
    fun BookCard(book: BookItem) {

        Card(

            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
                .fillMaxSize()

        ) {
            Column(modifier = Modifier.padding(10.dp)) {

                Text(text = book.title, fontWeight = FontWeight.Bold)
                Text(text = book.caption, maxLines = 2, overflow = TextOverflow.Ellipsis)

            }

        }

    }




    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        BookWithApiTheme {
            HomeScreen()
        }
    }

}

