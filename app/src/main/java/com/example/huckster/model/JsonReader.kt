package com.example.huckster.model

import android.app.Application
import com.example.huckster.R
import org.json.JSONArray
import org.json.JSONObject

class JsonReader(private val application: Application) {

    fun getItemByName(itemName: String): Item? {
        val file = application.assets.open("items.json")
        val text = file.bufferedReader().use { it.readText() }
        val array = JSONArray(text)

        for (i in 0 until array.length()) {
            val jsonObject = JSONObject(array[i].toString())

            if (jsonObject.get("name") == itemName) {
                val id = jsonObject.getInt("id")
                val name = jsonObject.getString("name")
                val author = jsonObject.getString("author")
                val price = jsonObject.getInt("price")
                val description = jsonObject.getString("description")

                val compositionJsonArray = jsonObject.getJSONArray("composition")
                val composition = jsonArrayToList(compositionJsonArray)

                return Item(name, author, getImageById(id), price, description, composition)
            }
        }

        return null
    }

    fun getAllItems(): List<Item> {
        val resultList = mutableListOf<Item>()

        val file = application.assets.open("items.json")
        val text = file.bufferedReader().use { it.readText() }
        val array = JSONArray(text)

        for (i in 0 until array.length()) {
            val jsonObject = JSONObject(array[i].toString())

            val id = jsonObject.getInt("id")
            val name = jsonObject.getString("name")
            val author = jsonObject.getString("author")
            val price = jsonObject.getInt("price")
            val description = jsonObject.getString("description")

            val compositionJsonArray = jsonObject.getJSONArray("composition")
            val composition = jsonArrayToList(compositionJsonArray)

            val item = Item(name, author, getImageById(id), price, description, composition)
            resultList.add(item)
        }

        return resultList
    }

    private fun jsonArrayToList(array: JSONArray): List<String> {
        val list = mutableListOf<String>()

        for (i in 0 until array.length()) {
            val element = array[i].toString()
            list.add(element)
        }

        return list
    }

    private fun getImageById(id: Int): Int {
        return when(id) {
            1-> R.drawable.img_balenciaga_track
            2 -> R.drawable.img_nike_free_tr_3
            3 -> R.drawable.img_nike_x_travis
            4 -> R.drawable.img_yeezy_foam_runner
            5 -> R.drawable.img_maison_margiela_fusion
            6 -> R.drawable.img_nike_x_kendrick_lamar
            7 -> R.drawable.img_converse_x_cdg
            8 -> R.drawable.img_nike_x_undercover
            9 -> R.drawable.img_nike_x_sean_wotherspoon
            10 -> R.drawable.img_gucci_flashtrek
            11 -> R.drawable.img_nike_blazer_mid_77
            else -> R.drawable.img_converse_x_fleur
        }
    }

}