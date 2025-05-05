package com.alancamargo.weapons.catalogue.ui.view

import android.graphics.drawable.Drawable
import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.common.ui.UiWeaponListHeader
import com.alancamargo.weapons.common.ui.UiWeaponType
import com.alancamargo.weapons.common.ui.UiYear
import com.alancamargo.weapons.core.ads.AdLoader
import com.alancamargo.weapons.core.design.view.ComposableAdView
import com.alancamargo.weapons.core.design.view.CustomFontText
import com.alancamargo.weapons.core.resources.ResourcesHelper
import com.alancamargo.weapons.core.design.R as CoreR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun WeaponListScreen(
    weapons: List<UiWeapon>?,
    weaponListWithHeader: List<Pair<UiWeaponListHeader?, List<UiWeapon>>>?,
    isProgressBarVisible: Boolean,
    isEmptyStateVisible: Boolean,
    isErrorVisible: Boolean,
    resourcesHelper: ResourcesHelper,
    adUnitId: String,
    adLoader: AdLoader,
    onItemClicked: (UiWeapon) -> Unit,
    onBackClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { CustomFontText(text = stringResource(CoreR.string.app_name)) },
                colors = TopAppBarColors(
                    containerColor = colorResource(CoreR.color.primary),
                    titleContentColor = colorResource(CoreR.color.white),
                    navigationIconContentColor = colorResource(CoreR.color.white),
                    actionIconContentColor = colorResource(CoreR.color.white),
                    scrolledContainerColor = colorResource(CoreR.color.primary)
                ),
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(CoreR.string.back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (isProgressBarVisible) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = colorResource(CoreR.color.accent)
                )
            } else {
                Column(Modifier.fillMaxSize()) {
                    when {
                        !weapons.isNullOrEmpty() -> WeaponListWithoutHeaderScreen(
                            weapons,
                            resourcesHelper,
                            onItemClicked
                        )

                        !weaponListWithHeader.isNullOrEmpty() -> WeaponListWithHeaderScreen(
                            weaponListWithHeader,
                            resourcesHelper,
                            onItemClicked
                        )

                        isEmptyStateVisible -> EmptyStateScreen()

                        isErrorVisible -> ErrorScreen()
                    }

                    Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_large)))

                    ComposableAdView(
                        modifier = Modifier.wrapContentSize()
                            .align(Alignment.CenterHorizontally),
                        adUnitId = adUnitId,
                        adLoader = adLoader
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun WeaponListWithoutHeaderScreenPreview() {
    WeaponListScreen(
        weapons = listOf(
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
        weaponListWithHeader = null,
        isEmptyStateVisible = false,
        isErrorVisible = false,
        isProgressBarVisible = false,
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
        adUnitId = "adUnitId",
        adLoader = object : AdLoader {
            override fun loadBannerAds(target: View) = Unit
        },
        onItemClicked = {},
        onBackClicked = {}
    )
}

@Preview(showSystemUi = true)
@Composable
private fun WeaponListWithHeaderScreenPreview() {
    WeaponListScreen(
        weapons = null,
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
        isEmptyStateVisible = false,
        isErrorVisible = false,
        isProgressBarVisible = false,
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
        adUnitId = "adUnitId",
        adLoader = object : AdLoader {
            override fun loadBannerAds(target: View) = Unit
        },
        onItemClicked = {},
        onBackClicked = {}
    )
}

@Preview(showSystemUi = true)
@Composable
private fun WeaponListLoadingScreenPreview() {
    WeaponListScreen(
        weapons = null,
        weaponListWithHeader = null,
        isEmptyStateVisible = false,
        isErrorVisible = false,
        isProgressBarVisible = true,
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
        adUnitId = "adUnitId",
        adLoader = object : AdLoader {
            override fun loadBannerAds(target: View) = Unit
        },
        onItemClicked = {},
        onBackClicked = {}
    )
}

@Preview(showSystemUi = true)
@Composable
private fun WeaponListEmptyStateScreenPreview() {
    WeaponListScreen(
        weapons = null,
        weaponListWithHeader = null,
        isEmptyStateVisible = true,
        isErrorVisible = false,
        isProgressBarVisible = false,
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
        adUnitId = "adUnitId",
        adLoader = object : AdLoader {
            override fun loadBannerAds(target: View) = Unit
        },
        onItemClicked = {},
        onBackClicked = {}
    )
}

@Preview(showSystemUi = true)
@Composable
private fun WeaponListErrorScreenPreview() {
    WeaponListScreen(
        weapons = null,
        weaponListWithHeader = null,
        isEmptyStateVisible = false,
        isErrorVisible = true,
        isProgressBarVisible = false,
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
        adUnitId = "adUnitId",
        adLoader = object : AdLoader {
            override fun loadBannerAds(target: View) = Unit
        },
        onItemClicked = {},
        onBackClicked = {}
    )
}
