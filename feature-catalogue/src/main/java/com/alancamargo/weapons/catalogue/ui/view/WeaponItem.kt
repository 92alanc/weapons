package com.alancamargo.weapons.catalogue.ui.view

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
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
import coil3.compose.AsyncImage
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.common.ui.UiWeaponType
import com.alancamargo.weapons.core.design.view.CustomFontText
import com.alancamargo.weapons.core.resources.ResourcesHelper
import com.alancamargo.weapons.core.design.R as CoreR

@Composable
internal fun WeaponItem(
    weapon: UiWeapon,
    resourcesHelper: ResourcesHelper,
    onItemClicked: (UiWeapon) -> Unit
) {
    OutlinedButton(
        modifier = Modifier.background(colorResource(CoreR.color.white)).fillMaxWidth(),
        shape = RoundedCornerShape(dimensionResource(CoreR.dimen.radius_card_view)),
        onClick = { onItemClicked(weapon) }
    ) {
        AsyncImage(
            modifier = Modifier.size(dimensionResource(CoreR.dimen.size_list_photo)),
            model = weapon.photos.firstOrNull(),
            contentDescription = null
        )

        Spacer(Modifier.width(dimensionResource(CoreR.dimen.margin_default)))
        CustomFontText(
            modifier = Modifier.fillMaxWidth(fraction = 0.85f),
            text = weapon.name,
            textAlign = TextAlign.Start,
            colour = colorResource(CoreR.color.black)
        )
        
        weapon.country?.let { country ->
            resourcesHelper.getDrawableResourceId(country.flagId)?.let { flagDrawableRes ->
                Spacer(Modifier.width(dimensionResource(CoreR.dimen.margin_default)))
                Image(
                    modifier = Modifier.width(dimensionResource(CoreR.dimen.size_flag))
                        .height(20.dp)
                        .border(width = 1.dp, color = colorResource(CoreR.color.black)),
                    contentScale = ContentScale.FillBounds,
                    imageVector = ImageVector.vectorResource(flagDrawableRes),
                    contentDescription = stringResource(country.name.nameRes)
                )
            }
        }
    }
}

@Preview
@Composable
private fun WeaponItemPreview() {
    WeaponItem(
        weapon = UiWeapon(
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
