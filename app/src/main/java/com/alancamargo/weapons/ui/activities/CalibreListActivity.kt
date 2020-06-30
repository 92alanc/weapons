package com.alancamargo.weapons.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.adapter.GenericTypeAdapter
import com.alancamargo.weapons.ui.entities.UiCalibre
import com.alancamargo.weapons.ui.queries.WeaponQuery
import com.alancamargo.weapons.ui.tools.hide
import com.alancamargo.weapons.ui.tools.loadAds
import com.alancamargo.weapons.ui.tools.show
import com.alancamargo.weapons.ui.viewmodel.CalibreViewModel
import kotlinx.android.synthetic.main.activity_country_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalibreListActivity : AppCompatActivity(R.layout.activity_simple_text_list),
    GenericTypeAdapter.OnItemClickListener<UiCalibre> {

    private val viewModel by viewModel<CalibreViewModel>()
    private val adapter = GenericTypeAdapter(this)

    private var state: CalibreViewModel.State? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView.adapter = adapter
        state = savedInstanceState?.getParcelable(KEY_STATE)
        state?.let(::processState) ?: fetchData()
        adView.loadAds()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, state)
    }

    override fun onItemClick(item: UiCalibre) {
        val query = WeaponQuery.ByCalibre(item.id)
        val intent = WeaponListActivity.getIntent(this, query)
        startActivity(intent)
    }

    private fun fetchData() {
        viewModel.getState().observe(this, Observer {
            state = it
            processState(it)
        })
    }

    private fun processState(state: CalibreViewModel.State) {
        when (state) {
            is CalibreViewModel.State.Ready -> displayCalibres(state.calibres)
            is CalibreViewModel.State.Loading -> showLoading()
            is CalibreViewModel.State.Error -> showError()
        }
    }

    private fun displayCalibres(calibres: List<UiCalibre>) {
        groupError.hide()
        adapter.setData(calibres)
        progressBar.hide()
    }

    private fun showLoading() {
        groupError.hide()
        progressBar.show()
    }

    private fun showError() {
        progressBar.hide()
        groupError.show()
    }

    private companion object {
        const val KEY_STATE = "state"
    }

}