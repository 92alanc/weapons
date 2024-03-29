package com.alancamargo.weapons.core.test.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import com.google.common.truth.Truth.assertThat

fun withRecyclerViewItemCount(expectedCount: Int): RecyclerViewItemCountAssertion {
    return RecyclerViewItemCountAssertion(expectedCount)
}

class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val recyclerView = view as RecyclerView
        assertThat(recyclerView.adapter?.itemCount).isEqualTo(expectedCount)
    }
}
