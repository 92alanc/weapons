package com.alancamargo.weapons.core.remoteconfig

interface RemoteConfigManager {

    suspend fun getString(key: String): String
}
