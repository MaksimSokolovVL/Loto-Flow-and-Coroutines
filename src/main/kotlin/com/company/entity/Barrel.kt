package com.company.entity

class Barrel(private val _number:Int) {
    fun getNumber():String = _number.toString()

    override fun toString(): String {
        return _number.toString()
    }
}