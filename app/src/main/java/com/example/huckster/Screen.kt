package com.example.huckster

import com.example.huckster.model.Item

sealed class Screen(val route: String) {
    object Main: Screen("mainScreen")
    object Product: Screen("{name}/itemScreen") {
        fun createRoute(name: String) = "$name/itemScreen"
    }
}
