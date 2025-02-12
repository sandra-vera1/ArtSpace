package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpace(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}





@Composable
fun ArtSpace(modifier: Modifier = Modifier) {
    var currentIndex by remember { mutableStateOf(0) }
    val artworks = listOf(R.drawable.art_1, R.drawable.art_2, R.drawable.art_3, R.drawable.art_4)
    val artworkTitle = listOf(stringResource(R.string.art_1_artwork_title),stringResource(R.string.art_2_artwork_title), stringResource(R.string.art_3_artwork_title), stringResource(R.string.art_4_artwork_title))
    val artworkArtist = listOf(stringResource(R.string.art_1_artwork_artist),stringResource(R.string.art_2_artwork_artist), stringResource(R.string.art_3_artwork_artist), stringResource(R.string.art_4_artwork_artist))
    val year = listOf(stringResource(R.string.art_1_year),stringResource(R.string.art_2_year), stringResource(R.string.art_3_year), stringResource(R.string.art_4_year))



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC0C0C0))
    ){
        ArtworkWall(currentIndex, artworks)
        Spacer(modifier = Modifier.height(50.dp))
        ArtworkDescriptor(artworkTitle[currentIndex], artworkArtist[currentIndex], year[currentIndex])
        Spacer(modifier = Modifier.height(25.dp))
        DisplayController(currentIndex, artworks.size) { newIndex ->
            currentIndex = newIndex
        }


    }
}


@Composable
fun ArtworkWall(currentIndex: Int, artworks: List<Int>){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .padding(15.dp)
                .shadow(8.dp, shape = RoundedCornerShape(8.dp)) // Adds shadow for depth
                .background(Color.White)
                .border(10.dp, Color.White, RoundedCornerShape(8.dp)) // White border

        ) {


            Image(
                painter = painterResource(id = artworks[currentIndex]),
                contentDescription = "Artwork Wall",
                modifier = Modifier
                    .size(500.dp)
                    .padding(bottom = 8.dp)
            )
        }
    }
}


@Composable
fun ArtworkDescriptor(artworkTitle: String, artworkArtist: String, year: String){
    Column(

        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF7488BA))
            .padding(40.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = artworkTitle,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 8.dp)

            )

        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = artworkArtist,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = " "+year,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

        }
    }
}


@Composable
fun DisplayController(currentIndex: Int, totalArtworks: Int, onIndexChange: (Int) -> Unit){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
             verticalAlignment = Alignment.CenterVertically,
             horizontalArrangement = Arrangement.spacedBy(16.dp),
             modifier = Modifier.fillMaxWidth().padding(16.dp)
          ) {
            Button(onClick = {if (currentIndex > 0) onIndexChange(currentIndex - 1 ) else onIndexChange(totalArtworks - 1)},modifier = Modifier.weight(1f)){
            Text(text= stringResource(R.string.btn_previous))

        }
            Spacer(modifier = Modifier.width(5.dp))
            Button(onClick = {if (currentIndex < totalArtworks - 1) onIndexChange(currentIndex + 1) else onIndexChange(0)},modifier = Modifier.weight(1f)){
            Text(text= stringResource(R.string.btn_next))
        }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        ArtSpace()
    }
}