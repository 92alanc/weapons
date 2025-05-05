package com.alancamargo.weapons.catalogue.ui.view

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.alancamargo.weapons.common.ui.UiCountry
import com.alancamargo.weapons.common.ui.UiMake
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.common.ui.UiWeaponListHeader
import com.alancamargo.weapons.common.ui.UiWeaponType
import com.alancamargo.weapons.core.design.view.CustomFontText
import com.alancamargo.weapons.core.resources.ResourcesHelper
import com.alancamargo.weapons.core.design.R as CoreR

@Composable
internal fun WeaponListWithHeaderItem(
    item: Pair<UiWeaponListHeader?, List<UiWeapon>>,
    resourcesHelper: ResourcesHelper,
    onItemClicked: (UiWeapon) -> Unit
) {
    val (header, weapons) = item

    Column(
        Modifier.fillMaxWidth()
            .padding(dimensionResource(CoreR.dimen.padding_small)),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(CoreR.dimen.margin_default)
        )
    ) {
        header?.let {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (it is UiCountry) {
                    resourcesHelper.getDrawableResourceId(it.flagId)?.let { flagDrawableRes ->
                        Image(
                            modifier = Modifier.width(dimensionResource(CoreR.dimen.size_flag))
                                .height(30.dp)
                                .border(width = 1.dp, color = colorResource(CoreR.color.black)),
                            contentScale = ContentScale.FillBounds,
                            imageVector = ImageVector.vectorResource(flagDrawableRes),
                            contentDescription = stringResource(it.name.nameRes)
                        )

                        Spacer(Modifier.width(dimensionResource(CoreR.dimen.margin_default)))
                    }
                }

                it.text?.let { headerText ->
                    CustomFontText(
                        text = headerText,
                        textSize = 18.sp
                    )
                }

                Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_default)))
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(CoreR.dimen.margin_default)
                )
            ) {
                items(weapons.size) { weaponIndex ->
                    val weapon = weapons[weaponIndex]
                    OutlinedButton(
                        modifier = Modifier.size(160.dp)
                            .padding(vertical = dimensionResource(CoreR.dimen.padding_small)),
                        shape = RoundedCornerShape(dimensionResource(CoreR.dimen.radius_card_view)),
                        onClick = { onItemClicked(weapon) }
                    ) {
                        Column {
                            AsyncImage(
                                modifier = Modifier.size(
                                    dimensionResource(CoreR.dimen.size_list_photo)
                                ),
                                model = weapon.photos.firstOrNull(),
                                contentDescription = null
                            )
                            Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_default)))
                            CustomFontText(
                                text = weapon.name,
                                colour = colorResource(CoreR.color.black),
                                textAlign = TextAlign.Center,
                                maxLines = 2
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WeaponListWithHeaderItemPreview() {
    WeaponListWithHeaderItem(
        item = UiMake(
            id = 1,
            name = "1907"
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
