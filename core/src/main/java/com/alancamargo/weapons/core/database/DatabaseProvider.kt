package com.alancamargo.weapons.core.database

import androidx.room.RoomDatabase
import kotlin.reflect.KClass

interface DatabaseProvider {

    fun <T : RoomDatabase> provideDatabaseFromAsset(
        clazz: KClass<T>,
        name: String,
        assetPath: String
    ): T
}
