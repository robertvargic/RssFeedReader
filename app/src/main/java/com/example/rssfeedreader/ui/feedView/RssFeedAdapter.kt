package com.example.rssfeedreader.ui.feedView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeedreader.R
import com.example.rssfeedreader.models.RssFeedUrl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_news_item.view.*
import me.toptas.rssconverter.RssItem

class RssFeedAdapter(
    private val items: List<RssItem>,
    private val context: Context,
    private val onNewsClickListener: OnNewsClickListener
) : RecyclerView.Adapter<RssFeedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_news_item, parent, false), onNewsClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View, private val onNewsClickListener: OnNewsClickListener) : RecyclerView.ViewHolder(view) {
        fun bindData(rssFeedItem: RssItem) {
            itemView.list_item_title.text = rssFeedItem.title
            itemView.list_item_date.text = rssFeedItem.publishDate
            itemView.setOnClickListener { rssFeedItem.link?.let { it1 -> onNewsClickListener.onClick(it1) } }
            Picasso.get().load(rssFeedItem.image).into(itemView.list_item_image_view)
        }
    }

    interface OnNewsClickListener {
        fun onClick(url: String)
    }
}