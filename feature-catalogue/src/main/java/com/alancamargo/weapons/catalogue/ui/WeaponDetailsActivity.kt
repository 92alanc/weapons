package com.alancamargo.weapons.catalogue.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.alancamargo.weapons.catalogue.databinding.ActivityWeaponDetailsBinding
import com.alancamargo.weapons.catalogue.ui.adapter.ViewPagerAdapter
import com.alancamargo.weapons.catalogue.ui.viewmodel.weapondetails.WeaponDetailsViewAction
import com.alancamargo.weapons.catalogue.ui.viewmodel.weapondetails.WeaponDetailsViewModel
import com.alancamargo.weapons.catalogue.ui.viewmodel.weapondetails.WeaponDetailsViewState
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.core.ads.AdLoader
import com.alancamargo.weapons.core.extensions.args
import com.alancamargo.weapons.core.extensions.createIntent
import com.alancamargo.weapons.core.extensions.observeFlow
import com.alancamargo.weapons.core.extensions.putArguments
import com.alancamargo.weapons.core.extensions.setDrawableOrHide
import com.alancamargo.weapons.core.extensions.setTextOrHide
import com.alancamargo.weapons.core.resources.ResourcesHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@AndroidEntryPoint
internal class WeaponDetailsActivity : AppCompatActivity() {

    private var _binding: ActivityWeaponDetailsBinding? = null

    private val binding: ActivityWeaponDetailsBinding
        get() = _binding!!

    private val args by args<Args>()
    private val viewModel by viewModels<WeaponDetailsViewModel>()

    @Inject
    lateinit var adLoader: AdLoader

    @Inject
    lateinit var resourcesHelper: ResourcesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWeaponDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
        observeViewModelFlows()
        viewModel.start(args.weapon)
    }

    override fun onBackPressed() {
        viewModel.onNativeBackClicked()
    }

    private fun setUpUi() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        adLoader.loadBannerAds(binding.banner)
    }

    private fun observeViewModelFlows() {
        observeFlow(viewModel.state, ::onStateChanged)
        observeFlow(viewModel.action, ::onAction)
    }

    private fun onStateChanged(state: WeaponDetailsViewState) = with(binding) {
        state.weapon?.let { weapon ->
            txtName.text = weapon.name
            txtType.text = weapon.type
            txtYear.setTextOrHide(weapon.year)
            txtCalibre.setTextOrHide(weapon.calibre)
            txtCountry.setTextOrHide(weapon.country?.name)
            imgFlag.setDrawableOrHide(weapon.country?.flagDrawable)
            txtCapacity.setTextOrHide(weapon.capacity)
            txtLength.setTextOrHide(weapon.length)
            txtManufacturer.setTextOrHide(weapon.manufacturer)
            txtEffectiveRange.setTextOrHide(weapon.effectiveRange)
            txtMass.setTextOrHide(weapon.mass)
            txtRateOfFire.setTextOrHide(weapon.rateOfFire)
            viewPager.adapter = ViewPagerAdapter(weapon.photos)
        }
    }

    private fun onAction(action: WeaponDetailsViewAction) = when (action) {
        is WeaponDetailsViewAction.Finish -> finish()
    }

    @Parcelize
    data class Args(val weapon: UiWeapon) : Parcelable

    companion object {
        fun getIntent(context: Context, args: Args): Intent {
            return context.createIntent<WeaponDetailsActivity>().putArguments(args)
        }
    }
}
