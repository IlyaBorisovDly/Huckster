package com.example.huckster

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.huckster.ui.theme.HucksterTheme
import com.example.huckster.ui.theme.Orange
import com.example.huckster.ui.theme.White

@Composable
fun Item() {
    Column {
        TopBar()
        Spacer(Modifier.height(8.dp))
        ItemImage()
        Spacer(Modifier.height(8.dp))
        ItemInfo()
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = {},
        backgroundColor = Color.White,
        navigationIcon = {
            IconButton(onClick = {}) {
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
fun ItemImage() {
    Box(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 48.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.nike_x_travis),
            contentDescription = "",
        )
    }
}

@Composable
fun ItemInfo() {
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
            NameAndPrice("Nike Air Max 270 x Travis Scott", "45 000")

            Title("Описание")
            Description("Дизайн модели разработан по мотивам винтажной экипировки для туризма. Основой обуви служит пыльный кремовый текстиль, дополняемый нубуком и флисом.")

            Title("Состав")
            Description("• Нейлон\n• Пластик\n• Полиэстер\n• Нубук\n• Термопластичный полиуритан")

            Title("Доставка и оплата")
            Description("Huckster является выдуманной торговой площадкой. Дважды подумайте прежде чем оплачивать товары, мы всё равно их не привезём")

            Spacer(Modifier.weight(1f))

            InteractionRow()
        }
    }
}

@Composable
fun NameAndPrice(name: String, price: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name, style = MaterialTheme.typography.h1)
        Text(text = "$price ₽", style = MaterialTheme.typography.body1)
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
            .padding(bottom = 32.dp),
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
    return if (isFavourite) R.drawable.ic_add_to_favourite else R.drawable.ic_add_to_favourite_filled
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
        Text("Добавить в корзину", style = MaterialTheme.typography.h2) //fontSize = 18.sp)
    }
}

@Preview
@Composable
fun ItemPreview() {
    HucksterTheme {
        Surface(Modifier.fillMaxSize()) {
            Item()
        }
    }
}