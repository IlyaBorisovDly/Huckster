package com.example.huckster

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.huckster.model.Item
import com.example.huckster.ui.theme.Orange
import com.example.huckster.ui.theme.White

@Composable
fun ItemScreen(navController: NavController, item: Item) {
    Column {
        TopBar(navController)
        Column(Modifier.verticalScroll(rememberScrollState())
        ) {
            ItemImage(item.image)
            ItemInfo(item)
        }
    }
}

@Composable
private fun TopBar(navController: NavController) {
    val offset = 1.dp

    TopAppBar(
        modifier = Modifier.padding(bottom = offset),
        elevation = offset,
        title = {},
        backgroundColor = Color.White,
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painterResource(R.drawable.ic_button_back),
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

@Composable
fun ItemImage(image: Int) {
    Box(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 48.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "Item image",
        )
    }
}

@Composable
fun ItemInfo(item: Item) {
    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        elevation = 10.dp
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            TitleAndPrice(item.name, item.author, item.getPrice())

            Title("Описание")
            Description(item.description)

            Title("Состав")
            Description(item.getComposition())

            Title("Доставка и оплата")
            Description("Huckster является выдуманной торговой площадкой. " +
                    "Дважды подумайте прежде чем оплачивать товары, мы всё равно их не привезём")

            InteractionRow()
        }
    }
}

@Composable
fun TitleAndPrice(name: String, author: String, price: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = name, style = MaterialTheme.typography.h1)
            Text(text = author, style = MaterialTheme.typography.caption)
        }

        Text(text = price, style = MaterialTheme.typography.body1)
    }
}

@Composable
fun Title(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h2,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun Description(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun InteractionRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        AddToFavouritesButton()
        Spacer(Modifier.width(16.dp))
        AddToCartButton()
    }
}

@Composable
fun AddToFavouritesButton(isFavourite: Boolean = false) {
    val isFav = remember { mutableStateOf(isFavourite) }
    val resource = remember { mutableStateOf(getResource(isFav.value)) }

    Button(
        modifier = Modifier
            .width(50.dp)
            .height(50.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = White),
        contentPadding = PaddingValues(0.dp),
        onClick = {
            isFav.value = !isFav.value
            resource.value = getResource(isFav.value)
        }
    ) {
        Image(painterResource(resource.value), "Add to favourites")
    }
}

private fun getResource(isFavourite: Boolean): Int {
    return if (isFavourite) R.drawable.ic_add_to_favourite_filled else R.drawable.ic_add_to_favourite
}

@Composable
fun AddToCartButton() {
    Button(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        elevation = ButtonDefaults.elevation(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Orange, contentColor = White),
        onClick = {}
    ) {
        Text("Добавить в корзину", style = MaterialTheme.typography.h2)
    }
}
