package com.alancamargo.weapons.ui.viewmodel

import com.alancamargo.weapons.core.arch.viewmodel.UiAction

sealed class WebViewUiAction : UiAction {

    object ShowLoading : WebViewUiAction()

    object HideLoading : WebViewUiAction()

    object Refresh : WebViewUiAction()

    object Finish : WebViewUiAction()

}
