package com.alancamargo.weapons.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.adapter.QueryAdapter
import com.alancamargo.weapons.ui.queries.WeaponQueryType
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
    }

    override fun onItemClick(query: WeaponQueryType) = when (query) {
        WeaponQueryType.ALL -> showAllWeapons()
        WeaponQueryType.BY_CALIBRE -> openCalibreSelector()
        WeaponQueryType.BY_COUNTRY -> openCountrySelector()
        WeaponQueryType.BY_MANUFACTURER -> openManufacturerSelector()
        WeaponQueryType.BY_NAME -> openNameSelector()
        WeaponQueryType.BY_TYPE -> openTypeSelector()
        WeaponQueryType.BY_YEAR -> openYearSelector()
    }

    private fun showAllWeapons() {
        
    }

    private fun openCalibreSelector() {

    }

    private fun openCountrySelector() {

    }

    private fun openManufacturerSelector() {

    }

    private fun openNameSelector() {

    }

    private fun openTypeSelector() {

    }

    private fun openYearSelector() {

    }

}