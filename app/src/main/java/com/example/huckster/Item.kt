package com.example.huckster

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.huckster.ui.theme.HucksterTheme

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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Nike Air Max 270 x Travis Scott", style = MaterialTheme.typography.h1)
                Text(text = "100 000 $", style = MaterialTheme.typography.body1)
            }
            Title("Описание")
            Description("Дизайн модели разработан по мотивам винтажной экипировки для туризма. Основой обуви служит пыльный кремовый текстиль, дополняемый нубуком и флисом.")

            Title("Состав")
            Description("• Нейлон\n• Пластик\n• Полиэстер\n• Нубук\n• Термопластичный полиуритан")

            Title("Доставка и оплата")
            Description("Huckster является выдуманной торговой площадкой. Дважды подумайте прежде чем оплачивать товары, мы всё равно их не привезём")

            Spacer(Modifier.weight(1f))
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Icon(painterResource(R.drawable.ic_add_to_favourite), "")
                Spacer(Modifier.width(16.dp))
                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6332), contentColor = Color.White),
                    onClick = {}
                ) {
                    Text("Добавить в корзину", fontSize = 18.sp)
                }
            }
        }
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

@Preview
@Composable
fun ItemPreview() {
    HucksterTheme {
        Surface(Modifier.fillMaxSize()) {
            Item()
        }
    }
}