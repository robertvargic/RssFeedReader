package com.example.rssfeedreader.ui.feedView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeedreader.R
import com.example.rssfeedreader.models.FeedItem
import kotlinx.android.synthetic.main.list_item_feed_item.view.*

class RssFeedAdapter (private val items: MutableList<FeedItem>, private val context: Context) : RecyclerView.Adapter<RssFeedAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_feed_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(rssFeedItem: FeedItem) {
            itemView.list_item_title.text = rssFeedItem.title
            itemView.list_item_link.text = rssFeedItem.link
        }
    }
}