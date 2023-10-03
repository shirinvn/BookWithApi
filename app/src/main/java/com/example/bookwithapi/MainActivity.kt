package com.example.bookwithapi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.bookwithapi.ui.theme.BookWithApiTheme




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BookWithApiTheme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {

                    composable("home") {
                        MainBookView(navController = navController)
                    }
                    composable("details") {
                        BookCaptionScreen()
                    }
                }




            }
        }
    }




    @Composable
    fun MainBookView(navController: NavHostController) {

        val viewModel = ViewModelProvider(this)[BookViewModel::class.java]
        viewModel.getAllBookSRequest()
        var bookList by remember { mutableStateOf(emptyList<BookResponceModel>()) }


        Column(modifier = Modifier.fillMaxSize()) {

            BookView(bookList = bookList, navController)

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
    fun BookView(bookList: List<BookResponceModel>, navController: NavHostController){
        LazyVerticalGrid(columns = GridCells.Adaptive(150.dp)
            , modifier = Modifier.fillMaxSize(),
            content = {
                items(bookList){book->

                    BoxCard(image = R.drawable.pic1, title =book.title, navController )

                }
            })
    }



    @Composable
    fun BookCaptionScreen(){

        val viewModel = ViewModelProvider(this)[BookViewModel::class.java]
        viewModel.getAllBookSRequest()
        var bookList by remember { mutableStateOf(emptyList<BookResponceModel>()) }



        Column(modifier = Modifier.fillMaxSize()) {
            BookScreen(bookList = bookList)
        }

        viewModel.bookList.observe(this) {books ->
            bookList=books

        }


        viewModel.postListError.observe(this) {isError ->
            isError?.let{

                Log.e("36356",isError)
            }

        }


        viewModel.loading.observe(this) {isLoading ->


            Log.e("36356",isLoading.toString())


        }



    }
}
