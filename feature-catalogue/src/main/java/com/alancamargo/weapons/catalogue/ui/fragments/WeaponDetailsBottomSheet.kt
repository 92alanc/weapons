package com.alancamargo.weapons.catalogue.ui.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.alancamargo.weapons.catalogue.ui.view.WeaponDetailsScreen
import com.alancamargo.weapons.catalogue.ui.viewmodel.weapondetails.WeaponDetailsViewModel
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.core.extensions.args
import com.alancamargo.weapons.core.extensions.putArguments
import com.alancamargo.weapons.core.resources.ResourcesHelper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@AndroidEntryPoint
internal class WeaponDetailsBottomSheet : BottomSheetDialogFragment() {

    private val args by args<Args>()
    private val viewModel by viewModels<WeaponDetailsViewModel>()

    @Inject
    lateinit var resourcesHelper: ResourcesHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                WeaponDetailsScreen(args.weapon, resourcesHelper)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onScreenViewed()
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
