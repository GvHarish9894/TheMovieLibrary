package com.techgv.themovielibrary.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.techgv.themovielibrary.R
import com.techgv.themovielibrary.data.remote.response.Movies

class ChildAdapter(private val listOfMovies:List<Movies>,private val context:Context):RecyclerView.Adapter<ChildAdapter.ViewHolder>() {

     var onChildClick:((Int)->Unit)? = null

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val imageView:ImageView = itemView.findViewById(R.id.imageView)
        init {
            itemView.setOnClickListener {
                onChildClick?.invoke(bindingAdapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.load(context.resources.getString(R.string.image_w780,listOfMovies[position].poster_path))
    }

    override fun getItemCount(): Int  = listOfMovies.size
}