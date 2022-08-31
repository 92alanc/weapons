package com.alancamargo.weapons.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.alancamargo.weapons.R
import com.alancamargo.weapons.databinding.ActivityMainBinding
import com.alancamargo.weapons.ui.adapter.QueryAdapter
import com.alancamargo.weapons.ui.fragments.NameSearchDialogue
import com.alancamargo.weapons.ui.navigation.WeaponListActivityNavigation
import com.alancamargo.weapons.ui.navigation.WebViewActivityNavigation
import com.alancamargo.weapons.ui.queries.WeaponQuery
import com.alancamargo.weapons.ui.queries.WeaponQueryType
import com.alancamargo.weapons.ui.tools.AdLoader
import com.alancamargo.weapons.ui.viewmodel.QueryViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), QueryAdapter.OnItemClickListener {

    private val viewModel by viewModel<QueryViewModel>()
    private val weaponListActivityNavigation by inject<WeaponListActivityNavigation>()
    private val adLoader by inject<AdLoader>()
    private val adapter = QueryAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.adapter = adapter
        adapter.setData(viewModel.getQueries())
        adLoader.loadBannerAds(binding.banner, R.string.banner_main)
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

    override fun onItemClick(query: WeaponQueryType) = when (query) {
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
        val navigation = get<WebViewActivityNavigation>()
        navigation.startActivity(
            context = this,
            titleRes = R.string.privacy_policy,
            url = getString(R.string.privacy_policy_url)
        )
    }

}