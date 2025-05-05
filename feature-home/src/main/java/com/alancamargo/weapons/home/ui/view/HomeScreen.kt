package com.alancamargo.weapons.home.ui.view

import android.view.View
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alancamargo.weapons.core.ads.AdLoader
import com.alancamargo.weapons.core.design.view.AD_VIEW_FRACTION
import com.alancamargo.weapons.core.design.view.ComposableAdView
import com.alancamargo.weapons.core.design.view.CustomFontText
import com.alancamargo.weapons.home.R
import com.alancamargo.weapons.home.ui.model.WeaponQueryType
import com.alancamargo.weapons.core.design.R as CoreR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    queryTypes: List<WeaponQueryType>,
    isConsentGranted: Boolean,
    adLoader: AdLoader,
    adUnitId: String,
    onAllWeaponsClicked: () -> Unit,
    onQueryItemClicked: (WeaponQueryType) -> Unit,
    onAboutClicked: () -> Unit,
    onPrivacyPolicyClicked: () -> Unit,
    onPrivacySettingsClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { CustomFontText(text = stringResource(CoreR.string.app_name)) },
                colors = TopAppBarColors(
                    containerColor = colorResource(CoreR.color.primary),
                    scrolledContainerColor = colorResource(CoreR.color.primary),
                    navigationIconContentColor = colorResource(CoreR.color.white),
                    titleContentColor = colorResource(CoreR.color.white),
                    actionIconContentColor = colorResource(CoreR.color.white)
                ),
                actions = {
                    HomeDropdownMenu(
                        onAboutClicked = onAboutClicked,
                        onPrivacyPolicyClicked = onPrivacyPolicyClicked,
                        onPrivacySettingsClicked = onPrivacySettingsClicked
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
                .padding(
                    top = dimensionResource(CoreR.dimen.padding_default),
                    start = dimensionResource(CoreR.dimen.padding_default),
                    end = dimensionResource(CoreR.dimen.padding_default)
                )
        ) {
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(dimensionResource(CoreR.dimen.radius_card_view)),
                onClick = onAllWeaponsClicked
            ) {
                WeaponQueryItem(
                    icon = ImageVector.vectorResource(R.drawable.ic_all_weapons),
                    label = stringResource(R.string.query_all_weapons)
                )
            }

            Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_default)))

            val fraction = if (isConsentGranted) {
                AD_VIEW_FRACTION
            } else {
                1f
            }
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction),
                columns = GridCells.Adaptive(minSize = 90.dp),
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(CoreR.dimen.margin_default)
                ),
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(CoreR.dimen.margin_default)
                )
            ) {
                items(queryTypes.size) { itemIndex ->
                    val queryType = queryTypes[itemIndex]

                    OutlinedButton(
                        modifier = Modifier.height(140.dp),
                        shape = RoundedCornerShape(dimensionResource(CoreR.dimen.radius_card_view)),
                        onClick = { onQueryItemClicked(queryType) }
                    ) {
                        WeaponQueryItem(
                            icon = ImageVector.vectorResource(queryType.iconId),
                            label = stringResource(queryType.labelId)
                        )
                    }
                }
            }

            if (isConsentGranted) {
                Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_large)))

                ComposableAdView(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterHorizontally),
                    adUnitId = adUnitId,
                    adLoader = adLoader
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        queryTypes = WeaponQueryType.entries.toList(),
        isConsentGranted = true,
        adLoader = object : AdLoader {
            override fun loadBannerAds(target: View) = Unit
        },
        adUnitId = "adUnitId",
        onAllWeaponsClicked = {},
        onQueryItemClicked = {},
        onAboutClicked = {},
        onPrivacyPolicyClicked = {},
        onPrivacySettingsClicked = {}
    )
}
