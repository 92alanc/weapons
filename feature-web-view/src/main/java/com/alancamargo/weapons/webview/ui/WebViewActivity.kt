package com.alancamargo.weapons.webview.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import com.alancamargo.weapons.core.ads.AdLoader
import com.alancamargo.weapons.core.extensions.args
import com.alancamargo.weapons.core.extensions.createIntent
import com.alancamargo.weapons.core.extensions.observeFlow
import com.alancamargo.weapons.core.extensions.putArguments
import com.alancamargo.weapons.webview.ui.view.WebViewScreen
import com.alancamargo.weapons.webview.ui.viewmodel.WebViewViewAction
import com.alancamargo.weapons.webview.ui.viewmodel.WebViewViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize
import javax.inject.Inject
import com.alancamargo.weapons.core.design.R as CoreR

@AndroidEntryPoint
internal class WebViewActivity : AppCompatActivity() {

    private val args by args<Args>()
    private val viewModel by viewModels<WebViewViewModel>()

    @Inject
    lateinit var adLoader: AdLoader

    private val loadContentState = mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebViewScreen(
                title = stringResource(args.titleRes),
                url = args.url,
                loadContentState = loadContentState,
                adUnitId = getString(CoreR.string.banner_web_view),
                adLoader = adLoader,
                onRefreshClicked = viewModel::onRefresh,
                onBackClicked = viewModel::onBackClicked
            )
        }
        observeFlow(viewModel.action, ::onActionChanged)
    }

    override fun onSupportNavigateUp(): Boolean {
        viewModel.onBackClicked()
        return true
    }

    private fun onActionChanged(action: WebViewViewAction) {
        when (action) {
            WebViewViewAction.Refresh -> loadContentState.value = true
            WebViewViewAction.Finish -> finish()
        }
    }

    @Parcelize
    data class Args(@StringRes val titleRes: Int, val url: String) : Parcelable

    companion object {
        fun getIntent(context: Context, args: Args): Intent {
            return context.createIntent<WebViewActivity>().putArguments(args)
        }
    }
}
