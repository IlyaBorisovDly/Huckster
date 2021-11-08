package com.example.huckster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.huckster.model.JsonReader
import com.example.huckster.ui.theme.HucksterTheme

@ExperimentalMaterialApi
@ExperimentalFoundationApi
class AppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HucksterTheme {
                App()
            }
        }
    }

    @Composable
    private fun App() {
        val reader = JsonReader(application)
        val itemsList = reader.getAllItems()

        val navController = rememberNavController()

        NavHost(navController, Screen.Main.route) {
            composable(Screen.Main.route) {
                MainScreen(navController, itemsList)
            }
            composable(Screen.SingleItem.route) { backStackEntry ->
                val name = backStackEntry.arguments?.getString("name")
                val jsonReader = JsonReader(application)

                val item = jsonReader.getItemByName(name!!)
                    ?: throw NullPointerException("item is null")

                ItemScreen(navController, item)
            }
        }
    }
}
