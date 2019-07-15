package com.example.rssfeedreader.ui.addNewFeedView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeedreader.R
import com.example.rssfeedreader.models.RssFeedUrl
import com.example.rssfeedreader.ui.feedView.RssFeedAdapter
import kotlinx.android.synthetic.main.list_item_feed_item.view.*
import me.toptas.rssconverter.RssItem

class AddNewFeedAdapter(
    private val items: List<RssFeedUrl>,
    private val context: Context,
    private val onRssFeedClickListener: OnRssFeedClickListener
) : RecyclerView.Adapter<AddNewFeedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_feed_item, parent, false), onRssFeedClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View, private val onRssFeedClickListener: OnRssFeedClickListener) :
        RecyclerView.ViewHolder(view) {
        fun bindData(rssFeedItem: RssFeedUrl) {
            itemView.list_item_title.text = rssFeedItem.url
            itemView.setOnClickListener { onRssFeedClickListener.onClick(rssFeedItem.url) }
        }
    }

    interface OnRssFeedClickListener {
        fun onClick(url: String)
    }
}