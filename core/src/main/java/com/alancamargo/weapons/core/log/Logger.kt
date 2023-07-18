package com.alancamargo.weapons.core.log

interface Logger {

    fun debug(message: String)

    fun error(throwable: Throwable)
}
