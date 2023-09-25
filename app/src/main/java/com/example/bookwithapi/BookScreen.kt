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


@Preview
@Composable
fun CaptionScreen(){

    
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



            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.End){

                Image(painter = painterResource(id = R.drawable.ic_launcher_background)
                    , contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp),
                contentScale = ContentScale.FillWidth)

                Text(text = "متن کتاب کامل",
                    modifier = Modifier
                        .padding(end = 15.dp, top = 15.dp),
                fontSize = 22.sp)
            }


        }
    }

}




