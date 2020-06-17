package com.alancamargo.weapons.data.repository

import com.alancamargo.weapons.domain.Weapon

interface WeaponRepository {

    suspend fun getWeapons(): List<Weapon>

}