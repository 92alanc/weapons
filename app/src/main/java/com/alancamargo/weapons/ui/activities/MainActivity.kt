package com.alancamargo.weapons.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.adapter.QueryAdapter
import com.alancamargo.weapons.ui.fragments.NameSearchDialogue
import com.alancamargo.weapons.ui.queries.WeaponQuery
import com.alancamargo.weapons.ui.queries.WeaponQueryType
import com.alancamargo.weapons.ui.tools.loadAds
import com.alancamargo.weapons.ui.viewmodel.QueryViewModel
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

    override fun onItemClick(query: WeaponQueryType) = when (query) {
        WeaponQueryType.ALL -> showAllWeapons()
        WeaponQueryType.BY_CALIBRE -> openCalibreListActivity()
        WeaponQueryType.BY_COUNTRY -> openCountryListActivity()
        WeaponQueryType.BY_MANUFACTURER -> openManufacturerListActivity()
        WeaponQueryType.BY_NAME -> openNameSearchDialogue()
        WeaponQueryType.BY_TYPE -> openTypeListActivity()
        WeaponQueryType.BY_YEAR -> openYearListActivity()
    }

    private fun showAllWeapons() {
        val intent = WeaponListActivity.getIntent(this, WeaponQuery.All)
        startActivity(intent)
    }

    private fun openCalibreListActivity() {
        val intent = Intent(this, CalibreListActivity::class.java)
        startActivity(intent)
    }

    private fun openCountryListActivity() {
        val intent = Intent(this, CountryListActivity::class.java)
        startActivity(intent)
    }

    private fun openManufacturerListActivity() {
        val intent = Intent(this, ManufacturerListActivity::class.java)
        startActivity(intent)
    }

    private fun openNameSearchDialogue() {
        val dialogue = NameSearchDialogue()
        dialogue.show(supportFragmentManager, NameSearchDialogue.TAG)
    }

    private fun openTypeListActivity() {
        val intent = Intent(this, TypeListActivity::class.java)
        startActivity(intent)
    }

    private fun openYearListActivity() {
        val intent = Intent(this, YearListActivity::class.java)
        startActivity(intent)
    }

}