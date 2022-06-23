package com.techgv.themovielibrary.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techgv.themovielibrary.R
import com.techgv.themovielibrary.data.remote.response.Movies

class CategoryDetailsAdapter( val list: MutableList<Movies>, val context: Context) :
    RecyclerView.Adapter<CategoryDetailsAdapter.ViewHolder>() {

    var onItemClick:((Int)->Unit)? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(bindingAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context)
            .load(context.resources.getString(R.string.image_w780,list[position].poster_path))
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = list.size
}