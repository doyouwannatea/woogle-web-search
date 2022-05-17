package com.example.woogle.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
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
        val item = webSearchItemList[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
            context.startActivity(intent)
        }
        holder.txt_title.text = item.title
        if (item.body?.isEmpty() == true) {
            holder.txt_desc.height = 0
        } else {
            holder.txt_desc.text = item.body
        }
    }

    override fun getItemCount() = webSearchItemList.size
}