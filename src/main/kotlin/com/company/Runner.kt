package com.company

import com.company.service.HostMan
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    val players = 3
    val countGameCard = 2
    val hostMan = HostMan()

    runBlocking {
        launch {
            println("Start Game ->  ->  ->  ->  -> ")
            hostMan.startGame(players, countGameCard)
            println(" ->  ->  ->  ->  -> End Game")
        }
    }
}


