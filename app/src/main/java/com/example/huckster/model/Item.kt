package com.example.huckster.model

data class Item(
    val name: String,
    val author: String,
    val image: Int,
    val price: Int,
    val description: String,
    val composition: List<String>,
) {

    fun getComposition(): String {
        val text = buildString {
            composition.forEach {
                append("• ")
                append(it)
                appendLine()
            }
        }.trim()

        return text
    }

    fun getPrice(): String {
        var result = ""
        var number = price
        var remainder: String

        while (number != 0) {
            if (number / 1000 == 0) {
                result = "$number $result"
                break
            } else {
                remainder = transformToString(number % 1000)
                number /= 1000

                result = "$remainder $result"
            }
        }

        return "$result₽"
    }

    private fun transformToString(remainder: Int): String {
        val transformed = buildString {
            append(remainder)

            while (this.length != 3) insert(0, "0")
        }

        return transformed
    }
}

