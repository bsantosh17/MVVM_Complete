package com.clikzop.mvvm_complete.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clikzop.mvvm_complete.databinding.Item2Binding
import com.clikzop.mvvm_complete.ui.dashboard.product2.ResponseProduct2Item

class AdapterProduct2(val context:Context, val list2:MutableList<ResponseProduct2Item>) :
    RecyclerView.Adapter<AdapterProduct2.MainViewHolder>() {

    class MainViewHolder(val binding:Item2Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = Item2Binding.inflate(inflate,parent,false)
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list2.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val list2 = list2[position]
        holder.binding.tvTitle.text = list2.name
        holder.binding.tvCategory.text = list2.slug
        Glide.with(context).load(list2.url).into(holder.binding.imageView2)
    }
}