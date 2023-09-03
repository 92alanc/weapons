package com.alancamargo.weapons.core.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

internal class PreferencesManagerImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : PreferencesManager {

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    override fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit {
            putBoolean(key, value)
        }
    }
}
