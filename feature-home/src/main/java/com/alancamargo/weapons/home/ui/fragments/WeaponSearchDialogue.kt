package com.alancamargo.weapons.home.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.alancamargo.weapons.common.ui.UiWeaponQuery
import com.alancamargo.weapons.core.extensions.observeFlow
import com.alancamargo.weapons.home.databinding.DialogueNameSearchBinding
import com.alancamargo.weapons.home.ui.viewmodel.weaponsearch.WeaponSearchViewAction
import com.alancamargo.weapons.home.ui.viewmodel.weaponsearch.WeaponSearchViewModel
import com.alancamargo.weapons.navigation.WeaponListActivityNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class WeaponSearchDialogue : DialogFragment() {

    private var _binding: DialogueNameSearchBinding? = null

    private val binding
        get() = _binding!!

    private val viewModel by viewModels<WeaponSearchViewModel>()

    @Inject
    lateinit var weaponListActivityNavigation: WeaponListActivityNavigation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogueNameSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFlow(viewModel.action, ::onAction)
        setUpUi()
    }

    override fun onResume() {
        super.onResume()
        val width = LinearLayout.LayoutParams.MATCH_PARENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(width, height)
    }

    private fun setUpUi() = with(binding) {
        btOk.setOnClickListener {
            val weaponName = edtSearch.text.toString()
            viewModel.onOkClicked(weaponName)
        }
    }

    private fun onAction(action: WeaponSearchViewAction) = when (action) {
        is WeaponSearchViewAction.NavigateToWeaponList -> navigateToWeaponList(action.query)
    }

    private fun navigateToWeaponList(query: UiWeaponQuery) {
        weaponListActivityNavigation.startActivity(
            context = requireContext(),
            query = query
        )
    }

    companion object {
        const val TAG = "name_search"
    }
}
