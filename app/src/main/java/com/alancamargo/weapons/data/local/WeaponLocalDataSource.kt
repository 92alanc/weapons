package com.alancamargo.weapons.data.local

import com.alancamargo.weapons.domain.Weapon

interface WeaponLocalDataSource {

    suspend fun getWeapons(): List<Weapon>

}