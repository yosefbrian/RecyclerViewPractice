package com.yudha.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by yudha on 17,August,2019
 */
class ListHeroAdapter(private val listHero: ArrayList<Hero>) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listHero.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, from, photo) = listHero[position]

        Glide.with(holder.itemView.context)
            .load(photo)
            .apply (RequestOptions().override(55, 55))
            .into(holder.imgPhoto)

        holder.tvName.text = name
        holder.tvForm.text = from

        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listHero[holder.adapterPosition])}
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvForm: TextView = itemView.findViewById(R.id.tv_item_from)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Hero)
    }
}

