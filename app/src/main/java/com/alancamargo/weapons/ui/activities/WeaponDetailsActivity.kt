package com.alancamargo.weapons.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import com.alancamargo.weapons.R
import com.alancamargo.weapons.databinding.ActivityWeaponDetailsBinding
import com.alancamargo.weapons.ui.adapter.ViewPagerAdapter
import com.alancamargo.weapons.ui.entities.UiWeapon
import com.alancamargo.weapons.ui.tools.AdLoader
import com.alancamargo.weapons.ui.tools.ResourcesHelper
import com.alancamargo.weapons.ui.tools.setDrawableOrHide
import com.alancamargo.weapons.ui.tools.setTextOrHide
import org.koin.android.ext.android.inject

class WeaponDetailsActivity : AppCompatActivity() {

    private val resourcesHelper by inject<ResourcesHelper>()
    private val imageLoader by inject<ImageLoader>()
    private val adLoader by inject<AdLoader>()
    private val weapon by lazy { intent.getParcelableExtra<UiWeapon>(EXTRA_WEAPON) }

    private lateinit var binding: ActivityWeaponDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeaponDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        weapon?.let(::bindTo)
        adLoader.loadBannerAds(binding.banner)
    }

    private fun bindTo(weapon: UiWeapon) {
        with(weapon) {
            binding.viewPager.adapter = ViewPagerAdapter(photos, imageLoader)
            binding.txtName.text = name

            val flag = country?.flagId?.let {
                resourcesHelper.getDrawable(it)
            }

            binding.imgFlag.setDrawableOrHide(flag)
            binding.txtCountry.setTextOrHide(country?.name)

            val yearText = resourcesHelper.getFormattedStringOrNull(
                R.string.year_format, year?.year
            )
            binding.txtYear.setTextOrHide(yearText)

            val manufacturerText = resourcesHelper.getFormattedStringOrNull(
                R.string.manufacturer_format, manufacturer?.name
            )
            binding.txtManufacturer.setTextOrHide(manufacturerText)

            binding.txtType.text = getString(R.string.type_format, type.name)

            val calibreText = resourcesHelper.getFormattedStringOrNull(
                R.string.calibre_format, calibre?.name
            )
            binding.txtCalibre.setTextOrHide(calibreText)

            val lengthText = resourcesHelper.getFormattedStringOrNull(
                R.string.length_format, lengthInMm
            )
            binding.txtLength.setTextOrHide(lengthText)

            val massText = resourcesHelper.getFormattedStringOrNull(
                R.string.mass_format, massInKg
            )
            binding.txtMass.setTextOrHide(massText)

            val capacityText = resourcesHelper.getPluralStringOrNull(
                R.plurals.capacity_plural, capacityInRounds
            )
            binding.txtCapacity.setTextOrHide(capacityText)

            val rateOfFireText = resourcesHelper.getFormattedStringOrNull(
                R.string.rate_of_fire_format, rateOfFireInRpm
            )
            binding.txtRateOfFire.setTextOrHide(rateOfFireText)

            val effectiveRangeText = resourcesHelper.getFormattedStringOrNull(
                R.string.effective_range_format, effectiveRangeInM
            )
            binding.txtEffectiveRange.setTextOrHide(effectiveRangeText)
        }
    }

    companion object {
        private const val EXTRA_WEAPON = "weapon"

        fun getIntent(context: Context, weapon: UiWeapon): Intent {
            return Intent(context, WeaponDetailsActivity::class.java)
                .putExtra(EXTRA_WEAPON, weapon)
        }
    }

}