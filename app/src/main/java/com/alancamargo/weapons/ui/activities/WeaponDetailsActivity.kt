package com.alancamargo.weapons.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.adapter.ViewPagerAdapter
import com.alancamargo.weapons.ui.entities.UiWeapon
import com.alancamargo.weapons.ui.tools.ResourcesHelper
import com.alancamargo.weapons.ui.tools.loadAds
import com.alancamargo.weapons.ui.tools.setDrawableOrHide
import com.alancamargo.weapons.ui.tools.setTextOrHide
import kotlinx.android.synthetic.main.activity_weapon_details.*
import org.koin.android.ext.android.inject

class WeaponDetailsActivity : AppCompatActivity(R.layout.activity_weapon_details) {

    private val resourcesHelper by inject<ResourcesHelper>()
    private val imageLoader by inject<ImageLoader>()
    private val weapon by lazy { intent.getParcelableExtra<UiWeapon>(EXTRA_WEAPON) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        weapon?.let(::bindTo)
        adView.loadAds()
    }

    private fun bindTo(weapon: UiWeapon) {
        with(weapon) {
            viewPager.adapter = ViewPagerAdapter(photos, imageLoader)
            txtName.text = name

            val flag = country?.flagId?.let {
                resourcesHelper.getDrawable(it)
            }

            imgFlag.setDrawableOrHide(flag)
            txtCountry.setTextOrHide(country?.name)

            val yearText = resourcesHelper.getFormattedStringOrNull(
                R.string.year_format, year?.year
            )
            txtYear.setTextOrHide(yearText)

            val manufacturerText = resourcesHelper.getFormattedStringOrNull(
                R.string.manufacturer_format, manufacturer?.name
            )
            txtManufacturer.setTextOrHide(manufacturerText)

            txtType.text = getString(R.string.type_format, type.name)

            val calibreText = resourcesHelper.getFormattedStringOrNull(
                R.string.calibre_format, calibre?.name
            )
            txtCalibre.setTextOrHide(calibreText)

            val lengthText = resourcesHelper.getFormattedStringOrNull(
                R.string.length_format, lengthInMm
            )
            txtLength.setTextOrHide(lengthText)

            val massText = resourcesHelper.getFormattedStringOrNull(
                R.string.mass_format, massInKg
            )
            txtMass.setTextOrHide(massText)

            val capacityText = resourcesHelper.getPluralStringOrNull(
                R.plurals.capacity_plural, capacityInRounds
            )
            txtCapacity.setTextOrHide(capacityText)

            val rateOfFireText = resourcesHelper.getFormattedStringOrNull(
                R.string.rate_of_fire_format, rateOfFireInRpm
            )
            txtRateOfFire.setTextOrHide(rateOfFireText)

            val effectiveRangeText = resourcesHelper.getFormattedStringOrNull(
                R.string.effective_range_format, effectiveRangeInM
            )
            txtEffectiveRange.setTextOrHide(effectiveRangeText)
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