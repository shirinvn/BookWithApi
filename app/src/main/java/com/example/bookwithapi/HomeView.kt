package com.example.bookwithapi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun BoxCard(image:Int, title:String ){
    Box(modifier = Modifier
        .height(120.dp)
        .width(100.dp)
        .clip(shape = RoundedCornerShape(15.dp))) {



        Card(modifier = Modifier
            .fillMaxSize()
            , colors = CardDefaults.cardColors(
                containerColor = Color.Red,
                contentColor = Color.White
            )) {

            Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {



                Image(painter = painterResource(id = image),
                    contentDescription = "")
                Text(text = title)
            }


        }
        
    }

}