package com.alancamargo.weapons.core.design.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alancamargo.weapons.core.design.R

@Composable
fun DotIndicators(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    onDotSelected: (Int) -> Unit
) {
    Row(
        modifier = modifier.padding(dimensionResource(R.dimen.padding_small)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (index in 0 until totalDots) {
            val isSelected = index == selectedIndex
            DotIndicator(isSelected, onClick = { onDotSelected(index) })

            if (index < totalDots - 1) {
                Spacer(Modifier.width(dimensionResource(R.dimen.margin_small)))
            }
        }
    }
}

@Composable
private fun DotIndicator(isSelected: Boolean, onClick: () -> Unit) {
    val colourRes = if (isSelected) {
        R.color.accent
    } else {
        R.color.grey
    }

    Box(
        modifier = Modifier.size(8.dp)
            .clip(CircleShape)
            .background(colorResource(colourRes))
            .clickable(onClick = onClick)
    )
}

@Preview(showBackground = true)
@Composable
private fun DotIndicatorsPreview() {
    DotIndicators(
        totalDots = 5,
        selectedIndex = 2,
        onDotSelected = {}
    )
}
