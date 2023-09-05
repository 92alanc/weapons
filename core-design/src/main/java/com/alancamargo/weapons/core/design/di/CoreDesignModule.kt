package com.alancamargo.weapons.core.design.di

import com.alancamargo.weapons.core.design.dialogue.DialogueHelper
import com.alancamargo.weapons.core.design.dialogue.DialogueHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class CoreDesignModule {

    @Binds
    @ActivityScoped
    abstract fun bindDialogueHelper(impl: DialogueHelperImpl): DialogueHelper
}
