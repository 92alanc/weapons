package com.alancamargo.weapons.home.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.alancamargo.weapons.common.ui.WeaponQuery
import com.alancamargo.weapons.core.design.ads.AdLoader
import com.alancamargo.weapons.home.R
import com.alancamargo.weapons.home.databinding.ActivityHomeBinding
import com.alancamargo.weapons.home.ui.adapter.QueryAdapter
import com.alancamargo.weapons.home.ui.fragments.NameSearchDialogue
import com.alancamargo.weapons.home.ui.model.WeaponQueryType
import com.alancamargo.weapons.home.ui.viewmodel.QueryViewModel
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

    private val viewModel by viewModels<QueryViewModel>()
    private val adapter by lazy { QueryAdapter(viewModel::onQueryItemClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.adapter = adapter
        adapter.submitList(viewModel.getQueries())
        adLoader.loadBannerAds(binding.banner)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemAbout -> showAppInfo()
            R.id.itemPrivacyPolicy -> showPrivacyNotice()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun onItemClick(query: WeaponQueryType) = when (query) {
        WeaponQueryType.ALL -> showAllWeapons()
        WeaponQueryType.BY_CALIBRE -> showWeaponsByCalibre()
        WeaponQueryType.BY_COUNTRY -> showWeaponsByCountry()
        WeaponQueryType.BY_MANUFACTURER -> showWeaponsByManufacturer()
        WeaponQueryType.BY_NAME -> openNameSearchDialogue()
        WeaponQueryType.BY_TYPE -> showWeaponsByType()
        WeaponQueryType.BY_YEAR -> showWeaponsByYear()
    }

    private fun showAllWeapons() {
        weaponListActivityNavigation.startActivity(this, WeaponQuery.All)
    }

    private fun showWeaponsByCalibre() {
        weaponListActivityNavigation.startActivity(this, WeaponQuery.ByCalibre)
    }

    private fun showWeaponsByCountry() {
        weaponListActivityNavigation.startActivity(this, WeaponQuery.ByCountry)
    }

    private fun showWeaponsByManufacturer() {
        weaponListActivityNavigation.startActivity(this, WeaponQuery.ByManufacturer)
    }

    private fun openNameSearchDialogue() {
        val dialogue = NameSearchDialogue()
        dialogue.show(supportFragmentManager, NameSearchDialogue.TAG)
    }

    private fun showWeaponsByType() {
        weaponListActivityNavigation.startActivity(this, WeaponQuery.ByType)
    }

    private fun showWeaponsByYear() {
        weaponListActivityNavigation.startActivity(this, WeaponQuery.ByYear)
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

    private fun showPrivacyNotice() {
        webViewActivityNavigation.startActivity(
            context = this,
            titleRes = R.string.privacy_policy,
            url = getString(R.string.privacy_policy_url)
        )
    }
}
