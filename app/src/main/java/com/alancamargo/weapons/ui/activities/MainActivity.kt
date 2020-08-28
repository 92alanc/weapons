package com.alancamargo.weapons.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.adapter.QueryAdapter
import com.alancamargo.weapons.ui.fragments.NameSearchDialogue
import com.alancamargo.weapons.ui.queries.WeaponQuery
import com.alancamargo.weapons.ui.queries.WeaponQueryType
import com.alancamargo.weapons.ui.tools.loadAds
import com.alancamargo.weapons.ui.viewmodel.QueryViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main), QueryAdapter.OnItemClickListener {

    private val viewModel by viewModel<QueryViewModel>()
    private val adapter = QueryAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView.adapter = adapter
        adapter.setData(viewModel.getQueries())
        adView.loadAds()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemAbout -> showAppInfo()
            R.id.itemPrivacyNotice -> showPrivacyNotice()
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
        val intent = WeaponListActivity.getIntent(this, WeaponQuery.All)
        startActivity(intent)
    }

    private fun showWeaponsByCalibre() {
        val intent = WeaponListActivity.getIntent(this, WeaponQuery.ByCalibre)
        startActivity(intent)
    }

    private fun showWeaponsByCountry() {
        val intent = WeaponListActivity.getIntent(this, WeaponQuery.ByCountry)
        startActivity(intent)
    }

    private fun showWeaponsByManufacturer() {
        val intent = WeaponListActivity.getIntent(this, WeaponQuery.ByManufacturer)
        startActivity(intent)
    }

    private fun openNameSearchDialogue() {
        val dialogue = NameSearchDialogue()
        dialogue.show(supportFragmentManager, NameSearchDialogue.TAG)
    }

    private fun showWeaponsByType() {
        val intent = WeaponListActivity.getIntent(this, WeaponQuery.ByType)
        startActivity(intent)
    }

    private fun showWeaponsByYear() {
        val intent = WeaponListActivity.getIntent(this, WeaponQuery.ByYear)
        startActivity(intent)
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
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.privacy_notice)
            .setMessage(R.string.privacy_notice_text)
            .show()
    }

}