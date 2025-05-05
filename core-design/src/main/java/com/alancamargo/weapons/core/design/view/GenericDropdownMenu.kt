package com.alancamargo.weapons.core.design.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.alancamargo.weapons.core.design.R

@Composable
fun GenericDropdownMenu(
    expandedState: MutableState<Boolean>,
    items: @Composable () -> Unit
) {
    Box(Modifier.wrapContentSize(Alignment.TopEnd)) {
        IconButton(
            onClick = { expandedState.value = true }
        ) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = stringResource(R.string.more)
            )

            DropdownMenu(
                expanded = expandedState.value,
                onDismissRequest = { expandedState.value = false }
            ) {
                items()
            }
        }
    }
}
