package com.alancamargo.weapons.home.ui.view

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.alancamargo.weapons.core.design.view.CustomFontText
import com.alancamargo.weapons.core.design.view.GenericDropdownMenu
import com.alancamargo.weapons.home.R

@Composable
internal fun HomeDropdownMenu(
    onAboutClicked: () -> Unit,
    onPrivacyPolicyClicked: () -> Unit,
    onPrivacySettingsClicked: () -> Unit
) {
    val expandedState = remember { mutableStateOf(false) }

    GenericDropdownMenu(expandedState) {
        DropdownMenuItem(
            text = { CustomFontText(text = stringResource(R.string.about)) },
            onClick = {
                onAboutClicked()
                expandedState.value = false
            }
        )
        DropdownMenuItem(
            text = { CustomFontText(text = stringResource(R.string.privacy_policy)) },
            onClick = {
                onPrivacyPolicyClicked()
                expandedState.value = false
            }
        )
        DropdownMenuItem(
            text = { CustomFontText(text = stringResource(R.string.privacy_settings)) },
            onClick = {
                onPrivacySettingsClicked()
                expandedState.value = false
            }
        )
    }
}
