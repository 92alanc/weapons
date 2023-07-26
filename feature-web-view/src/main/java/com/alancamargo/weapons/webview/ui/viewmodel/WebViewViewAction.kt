package com.alancamargo.weapons.webview.ui.viewmodel

internal sealed class WebViewViewAction {

    object Refresh : WebViewViewAction()

    object Finish : WebViewViewAction()
}
