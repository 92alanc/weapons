package com.alancamargo.weapons.webview.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.alancamargo.weapons.core.ads.AdLoader
import com.alancamargo.weapons.core.extensions.args
import com.alancamargo.weapons.core.extensions.createIntent
import com.alancamargo.weapons.core.extensions.observeFlow
import com.alancamargo.weapons.core.extensions.putArguments
import com.alancamargo.weapons.webview.R
import com.alancamargo.weapons.webview.databinding.ActivityWebViewBinding
import com.alancamargo.weapons.webview.ui.viewmodel.WebViewViewAction
import com.alancamargo.weapons.webview.ui.viewmodel.WebViewViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@AndroidEntryPoint
internal class WebViewActivity : AppCompatActivity() {

    private var _binding: ActivityWebViewBinding? = null

    private val binding
        get() = _binding!!

    private val args by args<Args>()
    private val viewModel by viewModels<WebViewViewModel>()

    @Inject
    lateinit var adLoader: AdLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
        observeFlow(viewModel.action, ::onActionChanged)
    }

    override fun onBackPressed() {
        viewModel.onNativeBackClicked()
    }

    override fun onSupportNavigateUp(): Boolean {
        viewModel.onBackClicked()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_web_view, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemRefresh -> {
                viewModel.onRefresh()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUpUi() {
        setUpToolbar()
        setUpWebView()
        adLoader.loadBannerAds(binding.banner)
    }

    private fun onActionChanged(action: WebViewViewAction) {
        when (action) {
            WebViewViewAction.Refresh -> binding.webView.loadUrl(args.url)
            WebViewViewAction.Finish -> finish()
        }
    }

    private fun setUpToolbar() = with(binding) {
        setSupportActionBar(toolbar)
        toolbar.setTitle(args.titleRes)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @Suppress("SetJavaScriptEnabled")
    private fun setUpWebView() {
        with(binding.webView) {
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    binding.progressBar.isVisible = true
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.progressBar.isVisible = false
                }
            }
            settings.javaScriptEnabled = true
            loadUrl(args.url)
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
