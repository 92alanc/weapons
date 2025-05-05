package com.alancamargo.weapons.core.design.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.alancamargo.weapons.core.design.R

@Composable
fun CustomTextField(
    label: String,
    textState: MutableState<String>
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(
            fontFamily = FontFamily(Font(R.font.inconsolata))
        ),
        colors = TextFieldColors(
            cursorColor = colorResource(R.color.accent),
            focusedLabelColor = colorResource(R.color.accent),
            textSelectionColors = TextSelectionColors(
                handleColor = colorResource(R.color.primary),
                backgroundColor = colorResource(R.color.primary)
            ),
            focusedIndicatorColor = colorResource(R.color.accent),
            unfocusedIndicatorColor = colorResource(R.color.primary),
            focusedContainerColor = Color.Unspecified,
            focusedTextColor = Color.Unspecified,
            unfocusedTextColor = Color.Unspecified,
            disabledTextColor = Color.Unspecified,
            errorTextColor = Color.Unspecified,
            unfocusedContainerColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified,
            errorContainerColor = Color.Unspecified,
            errorCursorColor = Color.Unspecified,
            disabledIndicatorColor = Color.Unspecified,
            errorIndicatorColor = Color.Unspecified,
            focusedLeadingIconColor = Color.Unspecified,
            unfocusedLeadingIconColor = Color.Unspecified,
            disabledLeadingIconColor = Color.Unspecified,
            errorLeadingIconColor = Color.Unspecified,
            focusedTrailingIconColor = Color.Unspecified,
            unfocusedTrailingIconColor = Color.Unspecified,
            disabledTrailingIconColor = Color.Unspecified,
            errorTrailingIconColor = Color.Unspecified,
            unfocusedLabelColor = Color.Unspecified,
            disabledLabelColor = Color.Unspecified,
            errorLabelColor = Color.Unspecified,
            focusedPlaceholderColor = Color.Unspecified,
            unfocusedPlaceholderColor = Color.Unspecified,
            disabledPlaceholderColor = Color.Unspecified,
            errorPlaceholderColor = Color.Unspecified,
            focusedSupportingTextColor = Color.Unspecified,
            unfocusedSupportingTextColor = Color.Unspecified,
            disabledSupportingTextColor = Color.Unspecified,
            errorSupportingTextColor = Color.Unspecified,
            focusedPrefixColor = Color.Unspecified,
            unfocusedPrefixColor = Color.Unspecified,
            disabledPrefixColor = Color.Unspecified,
            errorPrefixColor = Color.Unspecified,
            focusedSuffixColor = Color.Unspecified,
            unfocusedSuffixColor = Color.Unspecified,
            disabledSuffixColor = Color.Unspecified,
            errorSuffixColor = Color.Unspecified
        ),
        value = textState.value,
        onValueChange = { textState.value = it },
        label = { CustomFontText(text = label) },
        singleLine = true
    )
}

@Preview
@Composable
private fun CustomTextFieldPreview() {
    CustomTextField(
        label = "Search",
        textState = remember { mutableStateOf("") }
    )
}
