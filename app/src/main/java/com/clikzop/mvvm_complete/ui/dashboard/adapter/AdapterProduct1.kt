package com.clikzop.mvvm_complete.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clikzop.mvvm_complete.databinding.Item1Binding
import com.clikzop.mvvm_complete.ui.dashboard.product1.Product

class AdapterProduct1(private val context:Context, private val list:MutableList<Product>) :
    RecyclerView.Adapter<AdapterProduct1.MainViewHolder>() {


    class MainViewHolder(val binding:Item1Binding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
       val inflate = LayoutInflater.from(parent.context)
        val binding = Item1Binding.inflate(inflate,parent,false)
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val listitem = list[position]
        holder.binding.tvBrand.text = listitem.brand
        holder.binding.tvTitle.text = listitem.title
        holder.binding.tvPrice.text = listitem.price.toString()
        holder.binding.tvCategory.text = listitem.category
        Glide.with(context).load(listitem.thumbnail).into(holder.binding.imageView)
    }
}