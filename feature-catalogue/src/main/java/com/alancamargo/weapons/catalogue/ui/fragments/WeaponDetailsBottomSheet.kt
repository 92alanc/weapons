package com.alancamargo.weapons.catalogue.ui.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.alancamargo.weapons.catalogue.databinding.BottomSheetWeaponDetailsBinding
import com.alancamargo.weapons.catalogue.ui.adapter.ViewPagerAdapter
import com.alancamargo.weapons.catalogue.ui.viewmodel.weapondetails.WeaponDetailsViewModel
import com.alancamargo.weapons.catalogue.ui.viewmodel.weapondetails.WeaponDetailsViewState
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.core.extensions.args
import com.alancamargo.weapons.core.extensions.observeFlow
import com.alancamargo.weapons.core.extensions.putArguments
import com.alancamargo.weapons.core.extensions.setDrawableOrHide
import com.alancamargo.weapons.core.extensions.setTextOrHide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize

@AndroidEntryPoint
internal class WeaponDetailsBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetWeaponDetailsBinding? = null

    private val binding: BottomSheetWeaponDetailsBinding
        get() = _binding!!

    private val args by args<Args>()
    private val viewModel by viewModels<WeaponDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetWeaponDetailsBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFlow(viewModel.state, ::onStateChanged)
        viewModel.start(args.weapon)
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
            txtMake.setTextOrHide(weapon.make)
            txtEffectiveRange.setTextOrHide(weapon.effectiveRange)
            txtMass.setTextOrHide(weapon.mass)
            txtRateOfFire.setTextOrHide(weapon.rateOfFire)
            viewPager.adapter = ViewPagerAdapter(weapon.photos)
        }
    }

    @Parcelize
    data class Args(val weapon: UiWeapon) : Parcelable

    companion object {

        fun newInstance(weapon: UiWeapon): WeaponDetailsBottomSheet {
            val args = Args(weapon)
            return WeaponDetailsBottomSheet().putArguments(args)
        }
    }
}
