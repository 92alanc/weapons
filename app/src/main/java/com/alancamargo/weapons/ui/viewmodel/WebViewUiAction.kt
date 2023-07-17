package com.alancamargo.weapons.ui.viewmodel

sealed class WebViewUiAction {

    object ShowLoading : WebViewUiAction()

    object HideLoading : WebViewUiAction()

    object Refresh : WebViewUiAction()

    object Finish : WebViewUiAction()
}
