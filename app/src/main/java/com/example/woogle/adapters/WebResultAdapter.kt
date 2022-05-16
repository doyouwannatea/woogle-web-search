package com.example.woogle.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.woogle.R
import com.example.woogle.models.SearchResult

class WebResultAdapter(
    private val context: Context,
    private val webSearchItemList: MutableList<SearchResult>
) : RecyclerView.Adapter<WebResultAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_title: TextView = itemView.findViewById(R.id.txt_title)
        val txt_desc: TextView = itemView.findViewById(R.id.txt_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.web_page_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txt_title.text = webSearchItemList[position].title
        holder.txt_desc.text = webSearchItemList[position].body
    }

    override fun getItemCount() = webSearchItemList.size
}