package com.alancamargo.weapons.ui.activities

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.alancamargo.weapons.domain.Weapon
import com.alancamargo.weapons.ui.viewmodel.WeaponListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeaponListFragment : Fragment() {

    private val viewModel by viewModel<WeaponListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCommand().observe(viewLifecycleOwner, Observer {
            processCommand(it)
        })
    }

    private fun processCommand(command: WeaponListViewModel.Command?) {
        when (command) {
            is WeaponListViewModel.Command.Display -> displayWeapons(command.weapons)
            is WeaponListViewModel.Command.Error -> showError()
            is WeaponListViewModel.Command.Load -> showLoading()
        }
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