package com.alancamargo.weapons.home.ui.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.alancamargo.weapons.common.ui.UiWeaponQuery
import com.alancamargo.weapons.core.extensions.observeFlow
import com.alancamargo.weapons.home.ui.view.SearchByNameDialogue
import com.alancamargo.weapons.home.ui.viewmodel.weaponsearch.WeaponSearchViewAction
import com.alancamargo.weapons.home.ui.viewmodel.weaponsearch.WeaponSearchViewModel
import com.alancamargo.weapons.navigation.WeaponListActivityNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class WeaponSearchDialogue : DialogFragment() {

    private val viewModel by viewModels<WeaponSearchViewModel>()

    @Inject
    lateinit var weaponListActivityNavigation: WeaponListActivityNavigation

    private val textState = mutableStateOf("")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                SearchByNameDialogue(textState, viewModel::onOkClicked)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFlow(viewModel.action, ::onAction)
        viewModel.start()
    }

    override fun onResume() {
        super.onResume()
        val width = LinearLayout.LayoutParams.MATCH_PARENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(width, height)
    }

    override fun onCancel(dialog: DialogInterface) {
        viewModel.onCancel()
        super.onCancel(dialog)
    }

    private fun onAction(action: WeaponSearchViewAction) = when (action) {
        is WeaponSearchViewAction.NavigateToWeaponList -> navigateToWeaponList(action.query)
    }

    private fun navigateToWeaponList(query: UiWeaponQuery) {
        weaponListActivityNavigation.startActivity(
            context = requireContext(),
            query = query
        )
        dismiss()
    }

    companion object {
        const val TAG = "name_search"
    }
}
