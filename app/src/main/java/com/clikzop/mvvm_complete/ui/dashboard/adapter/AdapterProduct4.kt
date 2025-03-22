package com.clikzop.mvvm_complete.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clikzop.mvvm_complete.databinding.Item4Binding
import com.clikzop.mvvm_complete.ui.dashboard.product4.Product4Data

class AdapterProduct4(val context:Context, val list4:MutableList<Product4Data>) :RecyclerView.Adapter<AdapterProduct4.MainViewHolder>(){



    class MainViewHolder(val binding:Item4Binding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = Item4Binding.inflate(inflater,parent,false)
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list4.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
       val list4 = list4[position]
        holder.binding.tvPrice.text = list4.price.toString()
        holder.binding.tvTitle.text = list4.brand
        Glide.with(context).load(list4.thumbnail).into(holder.binding.imageView3)
    }
}