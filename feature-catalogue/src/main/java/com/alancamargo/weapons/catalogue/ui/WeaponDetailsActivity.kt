package com.alancamargo.weapons.catalogue.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import com.alancamargo.weapons.catalogue.R
import com.alancamargo.weapons.catalogue.databinding.ActivityWeaponDetailsBinding
import com.alancamargo.weapons.catalogue.ui.adapter.ViewPagerAdapter
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.core.ads.AdLoader
import com.alancamargo.weapons.core.extensions.args
import com.alancamargo.weapons.core.extensions.createIntent
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

    @Inject
    lateinit var adLoader: AdLoader

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var resourcesHelper: ResourcesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWeaponDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
    }

    private fun setUpUi() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        adLoader.loadBannerAds(binding.banner)

        with(args.weapon) {
            binding.viewPager.adapter = ViewPagerAdapter(photos)
            binding.txtName.text = name

            val flag = country?.flagId?.let {
                resourcesHelper.getDrawable(it)
            }

            binding.imgFlag.setDrawableOrHide(flag)
            binding.txtCountry.setTextOrHide(country?.name)

            val yearText = resourcesHelper.getFormattedStringOrNull(
                com.alancamargo.weapons.core.design.R.string.year_format, year?.year
            )
            binding.txtYear.setTextOrHide(yearText)

            val manufacturerText = resourcesHelper.getFormattedStringOrNull(
                com.alancamargo.weapons.core.design.R.string.manufacturer_format, manufacturer?.name
            )
            binding.txtManufacturer.setTextOrHide(manufacturerText)

            binding.txtType.text = getString(com.alancamargo.weapons.core.design.R.string.type_format, type.name)

            val calibreText = resourcesHelper.getFormattedStringOrNull(
                com.alancamargo.weapons.core.design.R.string.calibre_format, calibre?.name
            )
            binding.txtCalibre.setTextOrHide(calibreText)

            val lengthText = resourcesHelper.getFormattedStringOrNull(
                com.alancamargo.weapons.core.design.R.string.length_format, lengthInMm
            )
            binding.txtLength.setTextOrHide(lengthText)

            val massText = resourcesHelper.getFormattedStringOrNull(
                com.alancamargo.weapons.core.design.R.string.mass_format, massInKg
            )
            binding.txtMass.setTextOrHide(massText)

            val capacityText = resourcesHelper.getPluralStringOrNull(
                R.plurals.capacity_plural, capacityInRounds
            )
            binding.txtCapacity.setTextOrHide(capacityText)

            val rateOfFireText = resourcesHelper.getFormattedStringOrNull(
                com.alancamargo.weapons.core.design.R.string.rate_of_fire_format, rateOfFireInRpm
            )
            binding.txtRateOfFire.setTextOrHide(rateOfFireText)

            val effectiveRangeText = resourcesHelper.getFormattedStringOrNull(
                com.alancamargo.weapons.core.design.R.string.effective_range_format, effectiveRangeInM
            )
            binding.txtEffectiveRange.setTextOrHide(effectiveRangeText)
        }
    }

    @Parcelize
    data class Args(val weapon: UiWeapon) : Parcelable

    companion object {
        fun getIntent(context: Context, weapon: UiWeapon): Intent {
            val args = Args(weapon)
            return context.createIntent<WeaponDetailsActivity>().putArguments(args)
        }
    }
}
