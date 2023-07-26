package com.alancamargo.weapons.webview.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.weapons.core.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class WebViewViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _action = MutableSharedFlow<WebViewViewAction>()

    val action = _action.asSharedFlow()

    fun onRefresh() {
        sendAction(WebViewViewAction.Refresh)
    }

    fun onBackClicked() {
        sendAction(WebViewViewAction.Finish)
    }

    fun onNativeBackClicked() {
        sendAction(WebViewViewAction.Finish)
    }

    private fun sendAction(action: WebViewViewAction) {
        viewModelScope.launch(dispatcher) {
            _action.emit(action)
        }
    }
}
