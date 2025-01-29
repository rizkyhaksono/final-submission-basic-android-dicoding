package com.example.finalsubmissionbasicandroiddicoding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListAirlineAdapter(private val listAirline: ArrayList<Airlines>) : RecyclerView.Adapter<ListAirlineAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_airlines, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listAirline.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val airline = listAirline[position]
        Glide.with(holder.itemView.context)
            .load(airline.photo)
            .into(holder.imgPhoto)
        holder.tvName.text = airline.name
        holder.tvDescription.text = airline.description
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listAirline[holder.adapterPosition]) }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Airlines)
    }
}