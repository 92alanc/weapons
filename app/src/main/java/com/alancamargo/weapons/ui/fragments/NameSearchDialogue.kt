package com.alancamargo.weapons.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.activities.WeaponListActivity
import com.alancamargo.weapons.ui.queries.WeaponQuery
import kotlinx.android.synthetic.main.dialogue_name_search.*

class NameSearchDialogue : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialogue_name_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btOk.setOnClickListener {
            val query = prepareQuery()
            sendQuery(query)
        }
    }

    override fun onResume() {
        super.onResume()
        val width = LinearLayout.LayoutParams.MATCH_PARENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(width, height)
    }

    private fun prepareQuery(): WeaponQuery {
        val name = edtSearch.text.toString()
        return WeaponQuery.ByName(name)
    }

    private fun sendQuery(query: WeaponQuery) {
        val intent = WeaponListActivity.getIntent(requireContext(), query)
        requireContext().startActivity(intent)
    }

    companion object {
        const val TAG = "name_search"
    }

}