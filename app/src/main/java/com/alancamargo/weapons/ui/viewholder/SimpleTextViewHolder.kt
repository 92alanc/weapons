package com.alancamargo.weapons.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alancamargo.weapons.ui.entities.SimpleTextEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_simple_text.*

class SimpleTextViewHolder<T: SimpleTextEntity>(
    itemView: View
) : RecyclerView.ViewHolder(itemView), LayoutContainer {

    override val containerView: View? = itemView

    fun bindTo(item: T) {
        txtContent.text = item.getText()
    }

}