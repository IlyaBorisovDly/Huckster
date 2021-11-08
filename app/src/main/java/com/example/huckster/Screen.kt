package com.example.huckster

sealed class Screen(val route: String) {
    object Main: Screen("mainScreen")
    object SingleItem: Screen("{name}/singleItemScreen") {
        fun createRoute(name: String) = "$name/singleItemScreen"
    }
}
