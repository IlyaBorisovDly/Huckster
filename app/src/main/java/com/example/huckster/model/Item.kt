package com.example.huckster.model

data class Item(
    val name: String,
    val image: Int,
    val price: Int,
    val description: String,
    val composition: String,
    var isFavourite: Boolean = false
) {
    fun getStringPrice(): String {
        return "$price ₽"
    }
}

