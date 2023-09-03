package com.alancamargo.weapons.core.preferences

interface PreferencesManager {

    fun getBoolean(key: String, defaultValue: Boolean): Boolean

    fun setBoolean(key: String, value: Boolean)
}
