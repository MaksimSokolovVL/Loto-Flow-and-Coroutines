package com.company.service

import com.company.entity.Barrel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class HostMan {
//    private val _barrelsFlow = MutableStateFlow("0")
//    private val barrelsFlow = _barrelsFlow.asStateFlow()

    private val _barrelsFlow = MutableSharedFlow<String>(3, DEFAULT_BUFFER_SIZE, BufferOverflow.SUSPEND)
    private val barrelsFlow = _barrelsFlow.asSharedFlow()

    suspend fun startGame(player: Int, countGameCard: Int) {
        val gameProcess = GameProcess(player, countGameCard, barrelsFlow)
//        var tempBoolean = true
//        while (tempBoolean) {
//            _barrelsFlow.emit(Bag.getBarrel().getNumber())
//            if (gameProcess.isEndGame) tempBoolean = false
//            delay(100)
//        }
        repeat(Bag.bugSize()) {
            _barrelsFlow.emit(Bag.getBarrel().getNumber())
            if (gameProcess.isEndGame) return@repeat
            delay(200)
        }
    }
}

private object Bag {
    private var listBarrel = mutableListOf<Barrel>()
    fun getBarrel(): Barrel {
        listBarrel.shuffle()
        return listBarrel.removeFirst()
    }

    fun bugSize(): Int = listBarrel.size


    init {
        (1..90).forEach { number -> listBarrel.add(Barrel(number)) }
    }
}






