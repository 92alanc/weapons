package com.alancamargo.weapons.ui.viewmodel

import androidx.lifecycle.ViewModel

class WebViewViewModel : ViewModel() {

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
