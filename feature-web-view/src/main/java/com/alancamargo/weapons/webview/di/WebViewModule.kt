package com.alancamargo.weapons.webview.di

import com.alancamargo.weapons.webview.ui.analytics.WebViewAnalytics
import com.alancamargo.weapons.webview.ui.analytics.WebViewAnalyticsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class WebViewModule {

    @Binds
    @ViewModelScoped
    abstract fun bindWebViewAnalytics(impl: WebViewAnalyticsImpl): WebViewAnalytics
}
