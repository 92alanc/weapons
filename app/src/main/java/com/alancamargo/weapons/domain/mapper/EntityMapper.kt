package com.alancamargo.weapons.domain.mapper

interface EntityMapper<I, O> {

    fun map(input: I): O
}