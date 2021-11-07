package com.example.huckster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.huckster.model.JsonReader
import com.example.huckster.ui.theme.Black
import com.example.huckster.ui.theme.HucksterTheme

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val reader = JsonReader(application)
        val itemsList = reader.getAllItems()

        setContent {
            HucksterTheme {
                LazyVerticalGrid(modifier = Modifier.fillMaxSize(), cells = GridCells.Fixed(2)) {
                    items(items = itemsList, itemContent = { item ->
                        Card(
                            modifier = Modifier
                                .width(50.dp)
                                .height(250.dp)
                                .padding(8.dp)
                                .border(BorderStroke(0.5.dp, Black), RoundedCornerShape(8.dp)),
                              elevation = 0.dp
                        ) {
                            Column(Modifier.fillMaxSize().padding(8.dp)) {
                                Image(
                                    modifier = Modifier
                                        .width(150.dp)
                                        .height(150.dp)
                                        .padding(vertical = 8.dp)
                                        .align(Alignment.CenterHorizontally),
                                    painter = painterResource(item.image),
                                    contentDescription = ""
                                )
                                Spacer(Modifier.weight(1f))
                                Text(item.name, style = MaterialTheme.typography.body1)
                                Text(item.getStringPrice(), style = MaterialTheme.typography.body1)
                            }
                        }
                    } )
                }
            }
        }
    }
}

//LazyColumn(modifier = Modifier.fillMaxSize()) {
//    items(items = itemsList, itemContent = { item ->
//        Card() {
//            Column() {
//                Image(painterResource(item.image), "")
//                Text(item.name, style = MaterialTheme.typography.h2)
//                Text(item.getStringPrice(), style = MaterialTheme.typography.body1)
//            }
//        }
//    } )
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
}