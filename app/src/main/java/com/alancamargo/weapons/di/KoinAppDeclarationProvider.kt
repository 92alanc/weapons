package com.alancamargo.weapons.di

import android.app.Application
import com.alancamargo.weapons.data.di.DataModule
import com.alancamargo.weapons.framework.di.FrameworkModule
import com.alancamargo.weapons.ui.di.UiModule
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.KoinAppDeclaration

object KoinAppDeclarationProvider {

    fun provideAppDeclaration(application: Application): KoinAppDeclaration = {
        androidContext(application)
        DataModule.load()
        FrameworkModule.load()
        UiModule.load()
    }

}