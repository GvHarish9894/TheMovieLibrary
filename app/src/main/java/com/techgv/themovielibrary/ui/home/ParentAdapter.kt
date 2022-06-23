package com.techgv.themovielibrary.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techgv.themovielibrary.R
import com.techgv.themovielibrary.data.CategorySet

class ParentAdapter(val list: MutableList<CategorySet>,val context:Context) :
    RecyclerView.Adapter<ParentAdapter.ViewHolder>() {

    private lateinit var  childAdapter:ChildAdapter
     var onChildItemClickItem:((Int, Int)->Unit)? = null
     var onCategoryClick:((Int)->Unit)? = null


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val parentRecyclerView: RecyclerView = itemView.findViewById(R.id.parentRecyclerView)

        init {
            textView.setOnClickListener {
                onCategoryClick?.invoke(bindingAdapterPosition)
            }
        }
        
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.parent_recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = list[position].title.first
        childAdapter = ChildAdapter(list[position].item,context)
        holder.parentRecyclerView.adapter = childAdapter
        holder.parentRecyclerView.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
        childAdapter.onChildClick = {
            onChildItemClickItem?.invoke(it,position)
        }

    }

    override fun getItemCount(): Int = list.size

}