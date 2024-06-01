package com.example.artgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artgallery.ui.theme.ArtGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtGalleryTheme {
                ArtGalleryLayout()
            }
        }
    }
}
@Composable
fun ArtWall(image : Painter ,modifier: Modifier) {
    Image(painter = image,
        contentDescription =null,
        contentScale = ContentScale.FillBounds,
        modifier = modifier )
}

@Composable
fun ArtDescription(title : String ,desc : String,modifier: Modifier) {

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(text = title,
            fontSize = 35.sp,
            modifier = Modifier,
            //modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            lineHeight = 40.sp
        )
        Text(
            text = desc,
            modifier = Modifier
                .height(150.dp)
                .width(400.dp)
                .padding(
                    bottom = 20.dp,
                    top = 10.dp,
                    start = 15.dp,
                    end = 15.dp
                )

                .verticalScroll(rememberScrollState())
                //.background(color = Color(174, 173, 200))
                .align(Alignment.CenterHorizontally)
                ,
            textAlign = TextAlign.Justify,
            fontSize = 16.sp,

            )
    }
}

@Composable
fun DeviceController(previous : () -> Unit , next : () -> Unit){
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ){
        Button(onClick = previous) {
            Text(text ="Previous" )
        }
        Button(onClick = next) {
            Text(text = "Next")
        }
    }
}

@Composable
fun ArtGalleryLayout() {
    var pageNumber by remember { mutableStateOf(0)}
    val img= when(pageNumber) {
        0 -> R.drawable.image1
        1 -> R.drawable.image2
        2 -> R.drawable.image3
        3 -> R.drawable.image4
        4 -> R.drawable.image5
        5 -> R.drawable.image6
        6 -> R.drawable.image7
        7 -> R.drawable.image8
        else -> 0
    }
    val title = when(pageNumber) {
        0 -> R.string.image1_title
        1 -> R.string.image2_title
        2 -> R.string.image3_title
        3 -> R.string.image4_title
        4 -> R.string.image5_title
        5 -> R.string.image6_title
        6 -> R.string.image7_title
        7 -> R.string.image8_title
        else -> 0
    }
    val desc = when(pageNumber) {
        0 -> R.string.image1_desc
        1 -> R.string.image2_desc
        2 -> R.string.image3_desc
        3 -> R.string.image4_desc
        4 -> R.string.image5_desc
        5 -> R.string.image6_desc
        6 -> R.string.image7_desc
        7 -> R.string.image8_desc
        else -> 0
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(color = Color.LightGray)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,


    )
    {
        ArtWall(
            image = painterResource(id =img ),
            modifier = Modifier
                .padding(20.dp)
                .width(440.dp)
                .height(430.dp)
                .border(width = 30.dp, shape = RectangleShape, color = Color.White)
                .shadow(20.dp, shape = RectangleShape)
            ,



        )
        ArtDescription(
            desc = stringResource(id = desc),
            title = stringResource(id = title),
            modifier = Modifier
                .padding(bottom = 20.dp)
                .background(color = Color(184, 178, 243))

        )
        DeviceController(
            {
                if(pageNumber >0 )
                    pageNumber --
                else
                    pageNumber = 7

            },
            {
                pageNumber= (pageNumber+1 ) % 8
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtGalleryTheme {
       ArtGalleryLayout()
    }
}