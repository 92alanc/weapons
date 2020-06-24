package com.alancamargo.weapons.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.adapter.WeaponAdapter
import com.alancamargo.weapons.ui.entities.UiWeapon
import com.alancamargo.weapons.ui.tools.hide
import com.alancamargo.weapons.ui.tools.show
import com.alancamargo.weapons.ui.viewmodel.WeaponListViewModel
import kotlinx.android.synthetic.main.fragment_weapon_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeaponListFragment : Fragment(R.layout.fragment_weapon_list),
    WeaponAdapter.OnItemClickListener {

    private val viewModel by viewModel<WeaponListViewModel>()
    private val adapter = WeaponAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        viewModel.getCommand().observe(viewLifecycleOwner, Observer {
            processCommand(it)
        })
    }

    override fun onItemClick(weapon: UiWeapon) {
        // TODO
    }

    private fun processCommand(command: WeaponListViewModel.Command?) {
        when (command) {
            is WeaponListViewModel.Command.Display -> displayWeapons(command.weapons)
            is WeaponListViewModel.Command.Error -> showError()
            is WeaponListViewModel.Command.Load -> showLoading()
        }
    }

    private fun displayWeapons(weapons: List<UiWeapon>) {
        groupError.hide()
        adapter.setData(weapons)
        progressBar.hide()
    }

    private fun showError() {
        progressBar.hide()
        groupError.show()
    }

    private fun showLoading() {
        groupError.hide()
        progressBar.show()
    }

}