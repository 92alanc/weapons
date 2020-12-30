package com.alancamargo.weapons.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import com.alancamargo.weapons.databinding.DialogueNameSearchBinding
import com.alancamargo.weapons.ui.activities.WeaponListActivity
import com.alancamargo.weapons.ui.queries.WeaponQuery

class NameSearchDialogue : DialogFragment() {

    private var _binding: DialogueNameSearchBinding? = null

    private val binding get() = _binding!!

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
        binding.btOk.setOnClickListener {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun prepareQuery(): WeaponQuery {
        val name = binding.edtSearch.text.toString()
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