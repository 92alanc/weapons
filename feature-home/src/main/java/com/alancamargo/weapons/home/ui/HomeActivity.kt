package com.alancamargo.weapons.home.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.alancamargo.weapons.common.ui.UiWeaponQuery
import com.alancamargo.weapons.core.ads.AdLoader
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var adLoader: AdLoader

    @Inject
    lateinit var weaponListActivityNavigation: WeaponListActivityNavigation

    @Inject
    lateinit var webViewActivityNavigation: WebViewActivityNavigation

    private var _binding: ActivityHomeBinding? = null

    private val binding: ActivityHomeBinding
        get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()
    private val adapter by lazy { QueryAdapter(viewModel::onQueryItemClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
        observeViewModelFlows()
        viewModel.getQueryTypes()
    }

    private fun setUpUi() {
        binding.recyclerView.adapter = adapter
        adLoader.loadBannerAds(binding.banner)
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
        is HomeViewAction.ShowPrivacyPolicy -> showPrivacyPolicy()
    }

    private fun navigateToWeaponsList(query: UiWeaponQuery) {
        weaponListActivityNavigation.startActivity(context = this, query)
    }

    private fun showWeaponSearchDialogue() {
        val dialogue = WeaponSearchDialogue()
        dialogue.show(supportFragmentManager, WeaponSearchDialogue.TAG)
    }

    private fun showAppInfo() {
        val appName = getString(R.string.app_name)
        val appVersion = packageManager.getPackageInfo(packageName, 0).versionName
        val title = getString(R.string.app_name_format, appName, appVersion)

        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(R.string.about_message)
            .show()
    }

    private fun showPrivacyPolicy() {
        webViewActivityNavigation.startActivity(
            context = this,
            titleRes = R.string.privacy_policy,
            url = getString(R.string.privacy_policy_url)
        )
    }
}