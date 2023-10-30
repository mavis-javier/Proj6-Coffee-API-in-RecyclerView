package com.example.proj6_cyoapi_recycleredition

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CoffeeAdapter(private val coffeeImgList: List<String>, private val coffeeTitleList: List<String>,private val coffeeDescList: List<String>) : RecyclerView.Adapter<CoffeeAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coffeeImage: ImageView
        val coffeeTitle: TextView
        val coffeeDescription: TextView

        init {
            // Find our RecyclerView item's ImageView for future use
            coffeeImage = view.findViewById(R.id.coffee_image)
            coffeeTitle = view.findViewById(R.id.coffee_title)
            coffeeDescription = view.findViewById(R.id.coffee_description)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.coffee_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = coffeeImgList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.coffeeTitle.text = coffeeTitleList [position]
        holder.coffeeDescription.text = coffeeDescList[position]
        Glide.with(holder.itemView)
            .load(coffeeImgList[position])
            .centerCrop()
            .into(holder.coffeeImage)

    }
}