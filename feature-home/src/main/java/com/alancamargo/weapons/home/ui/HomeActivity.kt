package com.alancamargo.weapons.home.ui

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.alancamargo.weapons.common.ui.UiWeaponQuery
import com.alancamargo.weapons.core.ads.AdLoader
import com.alancamargo.weapons.core.design.dialogue.DialogueHelper
import com.alancamargo.weapons.core.extensions.observeFlow
import com.alancamargo.weapons.home.R
import com.alancamargo.weapons.home.databinding.ActivityHomeBinding
import com.alancamargo.weapons.home.ui.adapter.QueryAdapter
import com.alancamargo.weapons.home.ui.fragments.WeaponSearchDialogue
import com.alancamargo.weapons.home.ui.viewmodel.home.HomeViewAction
import com.alancamargo.weapons.home.ui.viewmodel.home.HomeViewModel
import com.alancamargo.weapons.home.ui.viewmodel.home.HomeViewState
import com.alancamargo.weapons.navigation.WeaponListActivityNavigation
import com.alancamargo.weapons.navigation.WebViewActivityNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.alancamargo.weapons.core.design.R as R2

@AndroidEntryPoint
internal class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var adLoader: AdLoader

    @Inject
    lateinit var weaponListActivityNavigation: WeaponListActivityNavigation

    @Inject
    lateinit var webViewActivityNavigation: WebViewActivityNavigation

    @Inject
    lateinit var dialogueHelper: DialogueHelper

    private var _binding: ActivityHomeBinding? = null

    private val binding: ActivityHomeBinding
        get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()
    private val adapter by lazy { QueryAdapter(viewModel::onQueryItemClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
        observeViewModelFlows()
        viewModel.start()
    }

    private fun setUpUi() = with(binding) {
        setSupportActionBar(toolbar)
        recyclerView.adapter = adapter
        adLoader.loadBannerAds(banner)
        btAllWeapons.setOnClickListener {
            viewModel.onAllWeaponsClicked()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemAbout -> viewModel.onAppInfoClicked()
            R.id.itemPrivacyPolicy -> viewModel.onPrivacyPolicyClicked()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun observeViewModelFlows() {
        observeFlow(viewModel.state, ::onStateChanged)
        observeFlow(viewModel.action, ::onAction)
    }

    private fun onStateChanged(state: HomeViewState) {
        state.queryTypes?.let(adapter::submitList)
    }

    private fun onAction(action: HomeViewAction) = when (action) {
        is HomeViewAction.NavigateToWeaponList -> navigateToWeaponsList(action.query)
        is HomeViewAction.ShowWeaponSearchDialogue -> showWeaponSearchDialogue()
        is HomeViewAction.ShowAppInfo -> showAppInfo()
        is HomeViewAction.ShowPrivacyPolicy -> showPrivacyPolicy(action.url)
        is HomeViewAction.ShowFirstAccessInformation -> showFirstAccessInformation()
    }

    private fun navigateToWeaponsList(query: UiWeaponQuery) {
        weaponListActivityNavigation.startActivity(context = this, query)
    }

    private fun showWeaponSearchDialogue() {
        val dialogue = WeaponSearchDialogue()
        dialogue.show(supportFragmentManager, WeaponSearchDialogue.TAG)
    }

    @Suppress("DEPRECATION")
    private fun showAppInfo() {
        val appName = getString(R2.string.app_name)
        val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getPackageInfo(
                packageName,
                PackageManager.PackageInfoFlags.of(0)
            )
        } else {
            packageManager.getPackageInfo(packageName, 0)
        }

        val appVersion = packageInfo.versionName
        val title = getString(R.string.app_name_format, appName, appVersion)

        dialogueHelper.showDialogue(
            context = this,
            title = title,
            messageRes = R.string.about_message
        )
    }

    private fun showPrivacyPolicy(url: String) {
        webViewActivityNavigation.startActivity(
            context = this,
            titleRes = R.string.privacy_policy,
            url = url
        )
    }

    private fun showFirstAccessInformation() {
        dialogueHelper.showDialogue(
            context = this,
            title = getString(R.string.information),
            messageRes = R.string.first_access_information_message,
            onDismiss = viewModel::onDisclaimerDismissed
        )
    }
}
