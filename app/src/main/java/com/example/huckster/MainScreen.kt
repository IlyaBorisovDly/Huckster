package com.example.huckster

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.huckster.model.Item
import com.example.huckster.ui.theme.Gray

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun MainScreen(navController: NavController, itemsList: List<Item>) {
    Column {
        TopBar()
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp),
            cells = GridCells.Fixed(2)
        ) {
            items(items = itemsList, itemContent = { item ->
                ItemCard(navController, item)
            } )
        }
    }
}

@Composable
private fun TopBar() {
    val offset = 1.dp

    TopAppBar(
        modifier = Modifier.padding(bottom = offset),
        elevation = offset,
        title = { Text(text = "Huckster", style = MaterialTheme.typography.h1) },
        backgroundColor = Color.White,
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    painterResource(R.drawable.ic_button_grid),
                    contentDescription = ""
                )
            }
        },
        actions = {
            Icon(painterResource(R.drawable.ic_favourites), "")
            Spacer(Modifier.width(8.dp))
            Icon(painterResource(R.drawable.ic_cart), "")
            Spacer(Modifier.width(8.dp))
        }
    )
}

@ExperimentalMaterialApi
@Composable
private fun ItemCard(navController: NavController, item: Item) {
    Card(
        modifier = Modifier
            .height(250.dp)
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .border(BorderStroke(0.3.dp, Gray), MaterialTheme.shapes.medium),
        elevation = 0.dp,
        onClick = {
            navController.navigate(Screen.SingleItem.createRoute(item.name))
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
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
            Text(item.name, style = MaterialTheme.typography.subtitle1)
            Text(item.author, style = MaterialTheme.typography.caption)
            Text(
                text = item.getPrice(),
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}