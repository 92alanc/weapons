package com.alancamargo.weapons.webview.di

import com.alancamargo.weapons.navigation.WebViewActivityNavigation
import com.alancamargo.weapons.webview.ui.navigation.WebViewActivityNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
internal abstract class WebViewNavigationModule {

    @Binds
    @ActivityScoped
    abstract fun bindWebViewActivityNavigation(
        impl: WebViewActivityNavigationImpl
    ): WebViewActivityNavigation
}
