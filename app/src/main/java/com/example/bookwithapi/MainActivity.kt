package com.example.bookwithapi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.bookwithapi.ui.theme.BookWithApiTheme
import kotlinx.coroutines.flow.emptyFlow


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
                    composable(
                        route = "details/{title}",
                        arguments = listOf(navArgument("title") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val bookTitle = backStackEntry.arguments!!.getString("title")
                        if (bookTitle != null) {
                            BookCaptionScreen(bookTitle)
                        }
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
    fun BookCaptionScreen(bookTitle:String){

        val viewModel = ViewModelProvider(this)[BookViewModel::class.java]
        viewModel.getCaptionReq(bookTitle)

        val caption= viewModel.bookCaption.value!!.caption
        var book by remember { mutableStateOf(BookResponceModel(bookTitle, caption) ) }

        Column(modifier = Modifier.fillMaxSize()) {
            book.let { BookScreen(book = it) }

        }

        viewModel.bookCaption.observe(this) {books ->
            book=books

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





    @Composable
    fun BookScreen(book :BookResponceModel){

        androidx.compose.material.Surface(
            modifier = Modifier
                .fillMaxSize(),
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red)
            ) {

                androidx.compose.material.TopAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                ) {

                    Spacer(modifier = Modifier.weight(1f))


                    androidx.compose.material.Text(
                        text = book.title,
                        modifier = Modifier
                            .padding(end = 15.dp), fontSize = 22.sp
                    )
                }


                Column(modifier = Modifier.size(400.dp)) {

                    Image(
                        painter = painterResource(id = R.drawable.pic1), contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(210.dp),
                        contentScale = ContentScale.FillWidth
                    )

                    androidx.compose.material.Text(
                        text = book.caption,
                        modifier = Modifier
                            .padding(end = 15.dp, top = 15.dp),
                        fontSize = 22.sp, color = Color.White
                    )
                }

            }


        }

    }

}
