package com.alancamargo.weapons.catalogue.ui.view

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.alancamargo.weapons.catalogue.R
import com.alancamargo.weapons.common.ui.UiCalibre
import com.alancamargo.weapons.common.ui.UiMake
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.common.ui.UiWeaponType
import com.alancamargo.weapons.common.ui.UiYear
import com.alancamargo.weapons.core.design.view.BottomSheetHandle
import com.alancamargo.weapons.core.design.view.CustomFontText
import com.alancamargo.weapons.core.design.view.DotIndicators
import com.alancamargo.weapons.core.resources.ResourcesHelper
import kotlinx.coroutines.launch
import com.alancamargo.weapons.core.design.R as CoreR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun WeaponDetailsScreen(
    weapon: UiWeapon,
    resourcesHelper: ResourcesHelper
) {
    val pagerState = rememberPagerState { weapon.photos.size }

    Column(
        modifier = Modifier.fillMaxWidth().padding(dimensionResource(CoreR.dimen.padding_default))
    ) {
        BottomSheetHandle(Modifier.align(Alignment.CenterHorizontally))
        Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_large)))

        HorizontalPager(pagerState) { pageIndex ->
            val photo = weapon.photos[pageIndex]

            AsyncImage(
                modifier = Modifier.fillMaxWidth().height(240.dp),
                model = photo,
                contentDescription = null
            )
        }

        Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_default)))

        val coroutineScope = rememberCoroutineScope()
        DotIndicators(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            totalDots = weapon.photos.size,
            selectedIndex = pagerState.currentPage,
            onDotSelected = { index ->
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
            }
        )

        Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_large)))

        CustomFontText(
            text = weapon.name,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            textSize = 24.sp
        )

        weapon.country?.let { country ->
            Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_default)))
            Row(Modifier.fillMaxWidth()) {
                resourcesHelper.getDrawableResourceId(country.flagId)?.let { flagResId ->
                    Image(
                        modifier = Modifier.width(dimensionResource(CoreR.dimen.size_flag))
                            .height(30.dp)
                            .border(width = 1.dp, color = colorResource(CoreR.color.black)),
                        contentScale = ContentScale.FillBounds,
                        painter = painterResource(flagResId),
                        contentDescription = null
                    )
                    Spacer(Modifier.width(dimensionResource(CoreR.dimen.margin_default)))
                }

                CustomFontText(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = stringResource(country.name.nameRes),
                    textSize = 18.sp
                )
            }
        }

        weapon.year?.let { year ->
            Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_default)))
            CustomFontText(
                text = stringResource(R.string.year_format, year.year),
                textSize = 18.sp
            )
        }

        weapon.make?.let { make ->
            Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_default)))
            CustomFontText(
                text = stringResource(R.string.make_format, make.name),
                textSize = 18.sp
            )
        }

        Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_default)))
        CustomFontText(
            text = stringResource(R.string.type_format, weapon.type.name),
            textSize = 18.sp
        )

        weapon.calibre?.let { calibre ->
            Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_default)))
            CustomFontText(
                text = stringResource(R.string.calibre_format, calibre.name),
                textSize = 18.sp
            )
        }

        weapon.lengthInMm?.let { length ->
            Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_default)))
            CustomFontText(
                text = stringResource(R.string.length_format, length),
                textSize = 18.sp
            )
        }

        weapon.massInKg?.let { mass ->
            Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_default)))
            CustomFontText(
                text = stringResource(R.string.mass_format, mass),
                textSize = 18.sp
            )
        }

        weapon.capacityInRounds?.let { capacity ->
            Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_default)))
            CustomFontText(
                text = pluralStringResource(R.plurals.capacity_plural, capacity, capacity),
                textSize = 18.sp
            )
        }

        weapon.rateOfFireInRpm?.let { rateOfFire ->
            Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_default)))
            CustomFontText(
                text = stringResource(R.string.rate_of_fire_format, rateOfFire),
                textSize = 18.sp
            )
        }

        weapon.effectiveRangeInM?.let { effectiveRange ->
            Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_default)))
            CustomFontText(
                text = stringResource(R.string.effective_range_format, effectiveRange),
                textSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WeaponDetailsScreenPreview() {
    WeaponDetailsScreen(
        weapon = UiWeapon(
            id = 1,
            name = "Short Magazine Lee-Enfield No.1 Mk.III",
            year = UiYear(
                id = 1,
                year = 1907
            ),
            make = UiMake(
                id = 1,
                name = "Royal Small Arms Factory Enfield"
            ),
            country = null,
            type = UiWeaponType(
                id = 1,
                name = "Bolt action rifle"
            ),
            lengthInMm = 1200,
            massInKg = 4f,
            calibre = UiCalibre(
                id = 1,
                name = ".303 British"
            ),
            capacityInRounds = 10,
            rateOfFireInRpm = 25,
            effectiveRangeInM = 400,
            photos = listOf("1", "2", "3", "4", "5")
        ),
        resourcesHelper = object : ResourcesHelper {
            override fun getString(stringId: Int): String = ""

            override fun getDrawable(resourceName: String): Drawable? = null

            override fun getDrawableResourceId(resourceName: String): Int? = null

            override fun getFormattedString(stringId: Int, arg: Any): String = ""

            override fun getPluralString(stringId: Int, quantity: Int): String = ""
        }
    )
}
