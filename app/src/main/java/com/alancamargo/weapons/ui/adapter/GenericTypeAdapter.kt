package com.alancamargo.weapons.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.entities.SimpleTextEntity
import com.alancamargo.weapons.ui.viewholder.SimpleTextViewHolder

class GenericTypeAdapter<T: SimpleTextEntity>(
    private val onItemClickListener: OnItemClickListener<T>
) : RecyclerView.Adapter<SimpleTextViewHolder<T>>() {

    private var data = emptyList<T>()

    fun setData(data: List<T>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleTextViewHolder<T> {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_simple_text, parent, false)
        return SimpleTextViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SimpleTextViewHolder<T>, position: Int) {
        val item = data[position]
        holder.bindTo(item)
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int = data.size

    interface OnItemClickListener<T> {
        fun onItemClick(item: T)
    }

}