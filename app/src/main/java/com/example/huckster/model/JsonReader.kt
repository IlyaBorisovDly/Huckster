package com.example.huckster.model

import android.app.Application
import com.example.huckster.R
import org.json.JSONArray
import org.json.JSONObject

class JsonReader(private val application: Application) {

    fun getAllItems(): List<Item> {
        val resultList = mutableListOf<Item>()

        val file = application.assets.open("items.json")
        val text = file.bufferedReader().use { it.readText() }
        val array = JSONArray(text)

        for (i in 0 until array.length()) {
            val jsonObject = JSONObject(array[i].toString())

            val id = jsonObject.getInt("id")
            val name = jsonObject.getString("name")
            val price = jsonObject.getInt("price")
            val description = jsonObject.getString("description")
            val composition = jsonObject.getString("composition")

            val item = Item(name, getImageById(id), price, description, composition)
            resultList.add(item)
        }

        return resultList
    }

    private fun getImageById(id: Int): Int {
        return when(id) {
            1 -> R.drawable.img_nike_x_travis
            2 -> R.drawable.img_nike_x_kendrick_lamar
            3 -> R.drawable.img_gucci_flashtrek
            4 -> R.drawable.img_nike_x_undercover
            5 -> R.drawable.img_nike_x_sean_wotherspoon
            6 -> R.drawable.img_nike_free_tr_3
            7 -> R.drawable.img_nike_blazer_mid_77
            8-> R.drawable.img_balenciaga_track
            9 -> R.drawable.img_yeezy_foam_runner
            10 -> R.drawable.img_maison_margiela_fusion
            11 -> R.drawable.img_converse_x_fleur
            else -> R.drawable.img_converse_x_cdg
        }
    }

}