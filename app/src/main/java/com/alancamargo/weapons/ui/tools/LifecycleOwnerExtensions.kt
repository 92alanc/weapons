package com.alancamargo.weapons.ui.tools

import androidx.lifecycle.LifecycleOwner
import com.alancamargo.weapons.core.arch.viewmodel.ActionViewModel
import com.alancamargo.weapons.core.arch.viewmodel.UiAction

fun <A : UiAction> LifecycleOwner.observeAction(
    viewModel: ActionViewModel<A>,
    handleAction: (A) -> Unit
) {
    viewModel.action.observe(this) { action ->
        handleAction(action)
    }
}
