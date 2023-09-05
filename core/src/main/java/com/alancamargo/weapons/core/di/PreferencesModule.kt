package com.alancamargo.weapons.core.di

import android.content.Context
import com.alancamargo.weapons.core.preferences.PreferencesManager
import com.alancamargo.weapons.core.preferences.PreferencesManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun providePreferencesManager(
        @ApplicationContext context: Context
    ): PreferencesManager {
        val fileName = "weapons-preferences"
        val sharedPreferences = context.getSharedPreferences(
            fileName,
            Context.MODE_PRIVATE
        )

        return PreferencesManagerImpl(sharedPreferences)
    }
}
