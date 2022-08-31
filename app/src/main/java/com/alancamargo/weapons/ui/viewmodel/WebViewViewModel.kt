package com.alancamargo.weapons.ui.viewmodel

import com.alancamargo.weapons.core.arch.viewmodel.ActionViewModel

class WebViewViewModel : ActionViewModel<WebViewUiAction>() {

    fun onStartLoading() {
        sendAction { WebViewUiAction.ShowLoading }
    }

    fun onFinishedLoading() {
        sendAction { WebViewUiAction.HideLoading }
    }

    fun onRefresh() {
        sendAction { WebViewUiAction.Refresh }
    }

    fun onBackClicked() {
        sendAction { WebViewUiAction.Finish }
    }

    fun onNativeBackClicked() {
        sendAction { WebViewUiAction.Finish }
    }

}
