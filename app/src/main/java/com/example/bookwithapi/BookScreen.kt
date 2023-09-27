package com.example.bookwithapi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BookScreen(bookList: List<BookResponceModel>){
    
    Surface(modifier = Modifier
        .fillMaxSize(),
        ) {

        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)) {

            TopAppBar(modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)) {

                Spacer(modifier = Modifier.weight(1f))


                Text(text = "اسم کتاب",
                    modifier = Modifier
                        .padding(end = 15.dp)
                , fontSize = 22.sp)
            }


            LazyColumn(modifier = Modifier.size(400.dp),
            content = {
                items(bookList){book->


                    Image(painter = painterResource(id = R.drawable.pic1)
                        , contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(210.dp),
                        contentScale = ContentScale.FillWidth)

                    Text(text = book.caption,
                        modifier = Modifier
                            .padding(end = 15.dp, top = 15.dp),
                        fontSize = 22.sp, color = Color.White
                    )
                }

            })





        }
    }

}




