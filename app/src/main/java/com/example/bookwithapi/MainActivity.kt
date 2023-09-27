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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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

                // Set up the navigation graph
                NavHost(navController = navController, startDestination = "home") {


                    composable("home") {
                        MainBookView(navController = navController)
                    }
                    composable("details") {
                        BookScreen()
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
    fun BoxCard(image:Int, title:String, navController:NavHostController ){


        Card(modifier = Modifier
            .height(190.dp)
            .width(100.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .padding(15.dp)
            .clickable {
                // Navigate to the details screen when the card is clicked
                navController.navigate("details")
            }
            , colors = CardDefaults.cardColors(
                containerColor = Color.Red,
                contentColor = Color.White
            )) {

            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {


                Image(painter = painterResource(id = image),
                    contentDescription = "",
                    modifier = Modifier.height(130.dp),
                    contentScale = ContentScale.FillWidth)

                Text(text = title, color = Color.White)
            }

        }

    }

    @Composable
    fun BookView(bookList: List<BookResponceModel>, navController: NavHostController){
        LazyVerticalGrid(columns = GridCells.Adaptive(150.dp)
            , modifier = Modifier.fillMaxSize(),
            content = {
                items(bookList){book->



                    BoxCard(image = R.drawable.pic1, title =book.title, navController )

                  /*  Text(text = book.title, color  = Color.White)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = book.caption, color= Color.White)*/

            }
            })
    }


/*

    @Preview
    @Composable
    fun HomeView(){


    }
*/

}

