package com.alancamargo.weapons.webview.ui.view

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.alancamargo.weapons.core.ads.AdLoader
import com.alancamargo.weapons.core.design.view.AD_VIEW_FRACTION
import com.alancamargo.weapons.core.design.view.ComposableAdView
import com.alancamargo.weapons.core.design.view.CustomFontText
import com.alancamargo.weapons.webview.R
import com.alancamargo.weapons.core.design.R as CoreR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun WebViewScreen(
    title: String,
    url: String,
    loadContentState: MutableState<Boolean>,
    adUnitId: String,
    adLoader: AdLoader,
    onRefreshClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { CustomFontText(text = title) },
                colors = TopAppBarColors(
                    containerColor = colorResource(CoreR.color.primary),
                    scrolledContainerColor = colorResource(CoreR.color.primary),
                    navigationIconContentColor = colorResource(CoreR.color.white),
                    titleContentColor = colorResource(CoreR.color.white),
                    actionIconContentColor = colorResource(CoreR.color.white)
                ),
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(CoreR.string.back)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onRefreshClicked) {
                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = stringResource(R.string.refresh)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding).fillMaxSize()) {
            var isProgressBarVisible by remember { mutableStateOf(false) }

            Column {
                AndroidView(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(AD_VIEW_FRACTION),
                    factory = ::WebView,
                    update = { webView ->
                        webView.webViewClient = object : WebViewClient() {
                            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                                super.onPageStarted(view, url, favicon)
                                isProgressBarVisible = true
                            }

                            override fun onPageFinished(view: WebView?, url: String?) {
                                super.onPageFinished(view, url)
                                isProgressBarVisible = false
                            }
                        }

                        if (loadContentState.value) {
                            webView.loadUrl(url)
                            loadContentState.value = false
                        }
                    }
                )

                Spacer(Modifier.height(dimensionResource(CoreR.dimen.margin_large)))

                ComposableAdView(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterHorizontally),
                    adUnitId = adUnitId,
                    adLoader = adLoader
                )
            }

            if (isProgressBarVisible) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = colorResource(CoreR.color.accent)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun WebViewScreenPreview() {
    WebViewScreen(
        title = "Privacy policy",
        url = "test.com",
        loadContentState = remember { mutableStateOf(true) },
        adUnitId = "adUnitId",
        adLoader = object : AdLoader {
            override fun loadBannerAds(target: View) = Unit
        },
        onRefreshClicked = {},
        onBackClicked = {}
    )
}
