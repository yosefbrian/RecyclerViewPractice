package com.yudha.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by yudha on 17,August,2019
 */
class GridHeroAdapter(private val list: ArrayList<Hero>): RecyclerView.Adapter<GridHeroAdapter.GridViewHolder>() {

    private lateinit var onItemClickCallBack: OnItemClickCallBack

    fun setOnItemClickCallback(onItemClickCallBack: OnItemClickCallBack){
        this.onItemClickCallBack = onItemClickCallBack
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_hero, parent, false)
        return GridViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(list[position].photo)
            .apply(RequestOptions.overrideOf(350, 350))
            .into(holder.imgPhoto)

        holder.itemView.setOnClickListener { onItemClickCallBack.onItemClick(list[holder.adapterPosition]) }
    }

    inner class GridViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    interface OnItemClickCallBack{
        fun onItemClick(data: Hero)
    }
}