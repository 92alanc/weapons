package com.alancamargo.weapons.home.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.alancamargo.weapons.core.design.view.CustomFontText
import com.alancamargo.weapons.core.design.view.CustomTextField
import com.alancamargo.weapons.home.R
import com.alancamargo.weapons.core.design.R as CoreR

@Composable
internal fun SearchByNameDialogue(
    textState: MutableState<String>,
    onOkClicked: (String) -> Unit
) {
    Column(
        Modifier.background(color = colorResource(CoreR.color.white))
            .padding(dimensionResource(CoreR.dimen.padding_default))
    ) {
        CustomFontText(
            text = stringResource(R.string.find_weapons_by_name),
            textSize = 18.sp
        )

        Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_default)))
        CustomTextField(
            label = stringResource(R.string.search),
            textState = textState
        )

        Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_large)))
        TextButton(
            modifier = Modifier.align(Alignment.End),
            onClick = { onOkClicked(textState.value) }
        ) {
            CustomFontText(
                text = stringResource(CoreR.string.ok),
                colour = colorResource(CoreR.color.accent)
            )
        }
    }
}

@Preview
@Composable
private fun SearchByNameDialoguePreview() {
    SearchByNameDialogue(
        textState = remember { mutableStateOf("") },
        onOkClicked = {}
    )
}
