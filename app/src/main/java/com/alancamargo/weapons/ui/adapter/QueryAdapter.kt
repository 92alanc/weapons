package com.alancamargo.weapons.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.queries.WeaponQueryType
import com.alancamargo.weapons.ui.viewholder.QueryViewHolder

class QueryAdapter(
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<QueryViewHolder>() {

    private var data = emptyArray<WeaponQueryType>()

    fun setData(data: Array<WeaponQueryType>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_query, parent, false)
        return QueryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QueryViewHolder, position: Int) {
        val query = data[position]
        holder.bindTo(query)
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(query)
        }
    }

    override fun getItemCount(): Int = data.size

    interface OnItemClickListener {
        fun onItemClick(query: WeaponQueryType)
    }

}