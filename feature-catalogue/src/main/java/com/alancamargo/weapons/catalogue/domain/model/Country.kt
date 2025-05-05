package com.alancamargo.weapons.catalogue.domain.model

data class Country(
    val id: Long,
    val name: CountryName,
    val flagId: String
) : WeaponListHeader
