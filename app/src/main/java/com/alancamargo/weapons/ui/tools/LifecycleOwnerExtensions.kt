package com.alancamargo.weapons.ui.tools

import androidx.lifecycle.LifecycleOwner

fun <A : UiAction> LifecycleOwner.observeAction(
    viewModel: ActionViewModel<A>,
    handleAction: (A) -> Unit
) {
    viewModel.action.observe(this) { action ->
        handleAction(action)
    }
}
