package com.alancamargo.weapons.catalogue.ui.view

import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.common.ui.UiWeaponListHeader
import com.alancamargo.weapons.common.ui.UiWeaponType
import com.alancamargo.weapons.common.ui.UiYear
import com.alancamargo.weapons.core.design.view.AD_VIEW_FRACTION
import com.alancamargo.weapons.core.resources.ResourcesHelper
import com.alancamargo.weapons.core.design.R as CoreR

@Composable
internal fun WeaponListWithHeaderScreen(
    weaponListWithHeader: List<Pair<UiWeaponListHeader?, List<UiWeapon>>>,
    resourcesHelper: ResourcesHelper,
    onItemClicked: (UiWeapon) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(AD_VIEW_FRACTION)
            .padding(dimensionResource(CoreR.dimen.padding_small)),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(CoreR.dimen.margin_default)
        )
    ) {
        items(weaponListWithHeader.size) { itemIndex ->
            val item = weaponListWithHeader[itemIndex]
            WeaponListWithHeaderItem(item, resourcesHelper, onItemClicked)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WeaponListWithHeaderScreenPreview() {
    WeaponListWithHeaderScreen(
        weaponListWithHeader = listOf(
            UiYear(
                id = 1,
                year = 1914
            ) to listOf(
                UiWeapon(
                    id = 85,
                    name = "Short Magazine Lee-Enfield No.4 Mk.I",
                    year = null,
                    make = null,
                    country = null,
                    type = UiWeaponType(
                        id = 1,
                        name = "Bolt action rifle"
                    ),
                    lengthInMm = null,
                    massInKg = null,
                    calibre = null,
                    capacityInRounds = null,
                    rateOfFireInRpm = null,
                    effectiveRangeInM = null,
                    photos = listOf("file:///android_asset/85/1.png")
                ),
                UiWeapon(
                    id = 102,
                    name = "Karabiner 98k",
                    year = null,
                    make = null,
                    country = null,
                    type = UiWeaponType(
                        id = 1,
                        name = "Bolt action rifle"
                    ),
                    lengthInMm = null,
                    massInKg = null,
                    calibre = null,
                    capacityInRounds = null,
                    rateOfFireInRpm = null,
                    effectiveRangeInM = null,
                    photos = listOf("file:///android_asset/102/1.png")
                )
            ),
            UiYear(
                id = 2,
                year = 1918
            ) to listOf(
                UiWeapon(
                    id = 85,
                    name = "Short Magazine Lee-Enfield No.4 Mk.I",
                    year = null,
                    make = null,
                    country = null,
                    type = UiWeaponType(
                        id = 1,
                        name = "Bolt action rifle"
                    ),
                    lengthInMm = null,
                    massInKg = null,
                    calibre = null,
                    capacityInRounds = null,
                    rateOfFireInRpm = null,
                    effectiveRangeInM = null,
                    photos = listOf("file:///android_asset/85/1.png")
                ),
                UiWeapon(
                    id = 102,
                    name = "Karabiner 98k",
                    year = null,
                    make = null,
                    country = null,
                    type = UiWeaponType(
                        id = 1,
                        name = "Bolt action rifle"
                    ),
                    lengthInMm = null,
                    massInKg = null,
                    calibre = null,
                    capacityInRounds = null,
                    rateOfFireInRpm = null,
                    effectiveRangeInM = null,
                    photos = listOf("file:///android_asset/102/1.png")
                )
            )
        ),
        resourcesHelper = object : ResourcesHelper {
            override fun getDrawable(resourceName: String): Drawable? {
                return null
            }

            override fun getDrawableResourceId(resourceName: String): Int? {
                return null
            }

            override fun getFormattedString(stringId: Int, arg: Any): String {
                return ""
            }

            override fun getPluralString(stringId: Int, quantity: Int): String {
                return ""
            }

            override fun getString(stringId: Int): String {
                return ""
            }
        },
        onItemClicked = {}
    )
}

