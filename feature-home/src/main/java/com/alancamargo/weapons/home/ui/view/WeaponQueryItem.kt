package com.alancamargo.weapons.home.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.alancamargo.weapons.core.design.view.CustomFontText
import com.alancamargo.weapons.home.R
import com.alancamargo.weapons.core.design.R as CoreR

@Composable
internal fun WeaponQueryItem(
    icon: ImageVector,
    label: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            tint = colorResource(CoreR.color.accent),
            contentDescription = null
        )
        Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_default)))
        CustomFontText(
            text = label,
            colour = colorResource(CoreR.color.black),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun WeaponQueryItemPreview() {
    WeaponQueryItem(
        icon = ImageVector.vectorResource(R.drawable.ic_all_weapons),
        label = "All weapons"
    )
}
