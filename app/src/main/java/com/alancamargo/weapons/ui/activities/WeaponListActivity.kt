package com.alancamargo.weapons.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.alancamargo.weapons.domain.Weapon
import com.alancamargo.weapons.ui.viewmodel.WeaponListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeaponListActivity : AppCompatActivity() {

    private val viewModel by viewModel<WeaponListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCommand().observe(this, Observer { command ->
            when (command) {
                is WeaponListViewModel.Command.Display -> displayWeapons(command.weapons)
                is WeaponListViewModel.Command.Error -> showError()
                is WeaponListViewModel.Command.Load -> showLoading()
            }
        })
    }

    private fun displayWeapons(weapons: List<Weapon>) {
        // TODO
    }

    private fun showError() {
        // TODO
    }

    private fun showLoading() {
        // TODO
    }

}