package com.clikzop.mvvm_complete.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clikzop.mvvm_complete.databinding.ItemRecyclerBinding

class AdapterDashboard(private val context: Context, val list: MutableList<String>) :
    RecyclerView.Adapter<AdapterDashboard.MainViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerBinding.inflate(inflate, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val listItem = list[position]
            holder.binding.tvItem.text = listItem
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class MainViewHolder(val binding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }


}

