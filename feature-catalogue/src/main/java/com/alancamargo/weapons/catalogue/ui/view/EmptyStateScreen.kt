package com.alancamargo.weapons.catalogue.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alancamargo.weapons.catalogue.R
import com.alancamargo.weapons.core.design.view.AD_VIEW_FRACTION
import com.alancamargo.weapons.core.design.view.CustomFontText
import com.alancamargo.weapons.core.design.R as CoreR

@Composable
internal fun EmptyStateScreen() {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(AD_VIEW_FRACTION),
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(64.dp)
                .align(Alignment.CenterHorizontally),
            imageVector = Icons.Filled.Info,
            contentDescription = null
        )

        Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_large)))
        CustomFontText(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(R.string.no_results),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyStateScreenPreview() {
    EmptyStateScreen()
}
