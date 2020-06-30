package com.alancamargo.weapons.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.adapter.ViewPagerAdapter
import com.alancamargo.weapons.ui.entities.UiWeapon
import com.alancamargo.weapons.ui.entities.conversions.fromUiToStringId
import com.alancamargo.weapons.ui.tools.ResourcesHelper
import com.alancamargo.weapons.ui.tools.loadAds
import kotlinx.android.synthetic.main.activity_weapon_details.*
import org.koin.android.ext.android.inject

class WeaponDetailsActivity : AppCompatActivity(R.layout.activity_weapon_details) {

    private val resourcesHelper by inject<ResourcesHelper>()
    private val weapon by lazy { intent.getParcelableExtra<UiWeapon>(EXTRA_WEAPON) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindTo(weapon)
        adView.loadAds()
    }

    private fun bindTo(weapon: UiWeapon) {
        with(weapon) {
            viewPager.adapter = ViewPagerAdapter(photos)
            txtName.text = name
            val flag = resourcesHelper.getDrawable(country.flagId)
            imgFlag.setImageDrawable(flag)
            txtCountry.text = country.name
            txtYear.text = getString(R.string.year_format, year.year)
            txtManufacturer.text = getString(R.string.manufacturer_format, manufacturer.name)
            val typeString = getString(type.fromUiToStringId())
            txtType.text = getString(R.string.type_format, typeString)
            txtCalibre.text = getString(R.string.calibre_format, calibre.name)
            txtLength.text = getString(R.string.length_format, length)
            txtWeight.text = getString(R.string.weight_format, weight)
            txtCapacity.text = getString(R.string.capacity_format, capacity)
            txtRateOfFire.text = getString(R.string.rate_of_fire_format, rateOfFire)
            txtAccuracy.text = getString(R.string.accuracy_format, accuracy)
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