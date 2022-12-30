package com.company.entity

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

class Player(private val name: String, countCards: Int = 1) {
    private val listCards = mutableListOf<GameCard>()
    var isWin = AtomicBoolean(false)
        private set

    init {
        createCard(countCards)
    }

    private fun createCard(value: Int) {
        repeat(value) {
            listCards.add(GameCard())
        }
    }

    suspend fun checkNumber(numberBarrel: String) {
        coroutineScope {
            launch(Dispatchers.IO) {
                listCards
                    .forEach { gameCard ->
                        for (i in 0..gameCard.gameCardsList.lastIndex) {
                            for (j in 0..gameCard.gameCardsList[i].lastIndex) {
                                if (gameCard.gameCardsList[i][j] == (numberBarrel)) {
                                    println("$name number Barrel:$numberBarrel and card num ${gameCard.gameCardsList[i][j]} ${Thread.currentThread().name}")
//                                    gameCard.gameCardsList[i][j] = "X"
                                    gameCard.setGameCardsList(i,j, "X")
                                    val number =
                                        gameCard.gameCardsList[i].stream().filter { x -> x == "X" }.toList().size
                                    if (number == 5) {
                                        println("\nWIN!!!!! ${name.uppercase()}")
                                        isWin.compareAndSet(false, true)
                                    }
                                }
                            }
                        }
                    }
            }
        }
    }

    fun showYourCards() {
        print("\nPlayers name: $name")
        listCards.forEach { gameCard ->
            gameCard.printInfo()
            println()
        }
    }
}