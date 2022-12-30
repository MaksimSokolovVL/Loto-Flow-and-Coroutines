package com.company.entity

class GameCard {
    private var numberList = mutableListOf<String>()
    var gameCardsList = MutableList(3) { MutableList(9) { "0" } }
        private set

    fun setGameCardsList(x: Int, y: Int, value: String) {
        gameCardsList[x][y] = value
    }

    init {
        (1..90).forEach { number -> numberList.add(number.toString()) }
        for (i in 0..gameCardsList.lastIndex) {
            var x = 0
            while (x < 5) {
                val value = getNumber()?.toInt()
                if (gameCardsList[i][getIndex(value!!)] == "0") {
                    gameCardsList[i][getIndex(value)] = value.toString()
                    x++
                }
            }
        }
    }

    fun printInfo() {
        gameCardsList.forEach { list ->
            list.forEachIndexed { index, i ->
                if (index % 9 == 0) println()
                if (i == "0") {
                    print("    |")
                } else
                    if (i.length == 1) {
                        print("  $i |")
                    } else {
                        print(" $i |")
                    }
            }
        }
    }

    private fun getIndex(value: Int): Int = when (
        value
    ) {
        in 1..9 -> 0
        in 10..19 -> 1
        in 20..29 -> 2
        in 30..39 -> 3
        in 40..49 -> 4
        in 50..59 -> 5
        in 60..69 -> 6
        in 70..79 -> 7
        in 80..90 -> 8
        else -> 0
    }

    private fun getNumber(): String? {
        numberList.shuffle()
        return numberList.removeFirstOrNull()
    }
}


