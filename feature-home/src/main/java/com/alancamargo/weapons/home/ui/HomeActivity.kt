package com.alancamargo.weapons.home.ui

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.alancamargo.weapons.common.ui.UiWeaponQuery
import com.alancamargo.weapons.core.ads.AdLoader
import com.alancamargo.weapons.core.consent.UserConsentManager
import com.alancamargo.weapons.core.design.dialogue.DialogueHelper
import com.alancamargo.weapons.core.extensions.observeFlow
import com.alancamargo.weapons.home.R
import com.alancamargo.weapons.home.ui.fragments.WeaponSearchDialogue
import com.alancamargo.weapons.home.ui.model.WeaponQueryType
import com.alancamargo.weapons.home.ui.view.HomeScreen
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
    lateinit var userConsentManager: UserConsentManager

    @Inject
    lateinit var adLoader: AdLoader

    @Inject
    lateinit var weaponListActivityNavigation: WeaponListActivityNavigation

    @Inject
    lateinit var webViewActivityNavigation: WebViewActivityNavigation

    @Inject
    lateinit var dialogueHelper: DialogueHelper

    private val viewModel by viewModels<HomeViewModel>()

    private val queryTypesState = mutableStateOf(emptyList<WeaponQueryType>())
    private val isConsentGrantedState = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen(
                queryTypes = queryTypesState.value,
                isConsentGranted = isConsentGrantedState.value,
                adLoader = adLoader,
                adUnitId = getString(R2.string.banner_home),
                onAllWeaponsClicked = viewModel::onAllWeaponsClicked,
                onQueryItemClicked = viewModel::onQueryItemClicked,
                onAboutClicked = viewModel::onAppInfoClicked,
                onPrivacyPolicyClicked = viewModel::onPrivacyPolicyClicked,
                onPrivacySettingsClicked = {
                    userConsentManager.showPrivacyOptions(activity = this)
                }
            )
        }
        getConsentIfRequired()
        observeViewModelFlows()
        viewModel.start()
    }

    private fun getConsentIfRequired() {
        userConsentManager.getConsentIfRequired(activity = this) {
            isConsentGrantedState.value = true
        }
    }

    private fun observeViewModelFlows() {
        observeFlow(viewModel.state, ::onStateChanged)
        observeFlow(viewModel.action, ::onAction)
    }

    private fun onStateChanged(state: HomeViewState) {
        state.queryTypes?.let {
            queryTypesState.value = it
        }
    }

    private fun onAction(action: HomeViewAction) = when (action) {
        is HomeViewAction.NavigateToWeaponList -> navigateToWeaponsList(action.query)
        is HomeViewAction.ShowWeaponSearchDialogue -> showWeaponSearchDialogue()
        is HomeViewAction.ShowAppInfo -> showAppInfo()
        is HomeViewAction.ShowPrivacyPolicy -> showPrivacyPolicy(action.url)
        is HomeViewAction.ShowFirstAccessInformation -> showFirstAccessInformation(action.text)
    }

    private fun navigateToWeaponsList(query: UiWeaponQuery) {
        weaponListActivityNavigation.startActivity(context = this, query)
    }

    private fun showWeaponSearchDialogue() {
        val dialogue = WeaponSearchDialogue()
        dialogue.show(supportFragmentManager, WeaponSearchDialogue.TAG)
    }

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

    private fun showFirstAccessInformation(text: String) {
        dialogueHelper.showDialogue(
            context = this,
            title = getString(R.string.information),
            message = text,
            onDismiss = viewModel::onDisclaimerDismissed
        )
    }
}
