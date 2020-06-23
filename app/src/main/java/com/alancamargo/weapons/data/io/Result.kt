package com.alancamargo.weapons.data.io

sealed class Result<out T> {

    data class Success<T>(val body: T) : Result<T>()

    object Error : Result<Nothing>()

}