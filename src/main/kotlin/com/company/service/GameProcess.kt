package com.company.service

import com.company.entity.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class GameProcess(numberPlayers: Int, numberCards: Int, barrelsFlow: SharedFlow<String>) {
    private val listPlayers = mutableListOf<Player>()
    var isEndGame = false
        private set
    private val gameProcessScope = CoroutineScope(Dispatchers.IO)

    init {
        createPlayers(numberPlayers, numberCards)
        gameProcessScope.launch(Dispatchers.IO) {
            barrelsFlow.collect { numberBarrel ->
                println("Barrel number:$numberBarrel.")
                listPlayers.forEach { player ->
                    launch(Dispatchers.IO) {
                        player.checkNumber(numberBarrel)
                        if (player.isWin.get()) {
                            isEndGame = true
                            printAllGameCards()
                            gameProcessScope.cancel()
                        }
                    }
                }
            }
        }
    }

    private fun createPlayers(numberPlayers: Int, numberCards: Int) {
        for (i in 1..numberPlayers) {
            listPlayers.add(Player("Player $i", numberCards))
        }
    }

    private fun printAllGameCards() {
        listPlayers.forEach { player -> player.showYourCards() }
    }
}