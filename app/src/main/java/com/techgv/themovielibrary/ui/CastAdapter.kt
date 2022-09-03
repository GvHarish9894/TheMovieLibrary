package com.techgv.themovielibrary.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.techgv.themovielibrary.R
import com.techgv.themovielibrary.data.remote.response.Cast

class CastAdapter(var list: List<Cast>, var context: Context) :
    RecyclerView.Adapter<CastAdapter.ViewHolder>() {
     var onItemClickListener: ((Int) -> Unit)? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.castName)
        val image: ImageView = itemView.findViewById(R.id.castImage)
        init {
            image.setOnClickListener {
                onItemClickListener?.invoke(bindingAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cast_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(context.getString(R.string.original, list[position].profile_path))
            .apply(RequestOptions().override(0, 120))
            .into(holder.image)
        holder.name.text = list[position].name

    }

    override fun getItemCount(): Int = list.size
}