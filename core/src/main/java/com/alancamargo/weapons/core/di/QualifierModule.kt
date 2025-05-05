package com.alancamargo.weapons.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

private const val DEFAULT_VIEW_ACTION_DELAY_MILLIS = 50L

@Module
@InstallIn(SingletonComponent::class)
internal object QualifierModule {

    @Provides
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @ViewActionDelay
    fun provideViewActionDelay(): Long = DEFAULT_VIEW_ACTION_DELAY_MILLIS
}
