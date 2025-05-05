package com.alancamargo.weapons.core.design.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alancamargo.weapons.core.design.R

@Composable
fun BottomSheetHandle(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.width(60.dp)
            .height(20.dp)
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(RoundedCornerShape(4.dp))
            .background(colorResource(R.color.grey))
    )
}

@Preview(showBackground = true)
@Composable
private fun BottomSheetHandlePreview() {
    BottomSheetHandle()
}
