package com.example.rssfeedreader.ui.feedView

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rssfeedreader.R
import com.example.rssfeedreader.ui.base.BaseActivity
import me.toptas.rssconverter.RssFeed
import android.net.Uri
import com.example.rssfeedreader.database.UrlDatabase
import com.example.rssfeedreader.models.RssFeedUrl
import kotlinx.android.synthetic.main.activity_rss_feed.*
import me.toptas.rssconverter.RssItem


class RssFeedActivity : BaseActivity(), RssFeedContract.View {

    override fun initListView(feedItem: RssFeed) {
        val listener = object : RssFeedAdapter.OnNewsClickListener {
            override fun onClick(url: String) {
                val browserIntent = Intent(Intent.ACTION_VIEW)
                browserIntent.data = Uri.parse(url)
                startActivity(browserIntent)
            }
        }
        val rssFeedAdapter = feedItem.items?.let { RssFeedAdapter(it, this, listener) }
        activity_rss_feed_recycler_view.adapter = rssFeedAdapter
        rssFeedAdapter?.notifyDataSetChanged()
    }

    lateinit var rssFeedPresenter: RssFeedContract.Presenter

    override fun setPresenter(presenter: RssFeedContract.Presenter) {
        rssFeedPresenter = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rss_feed)
//        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)

        setPresenter(RssFeedPresenter(this))
        activity_rss_feed_recycler_view.layoutManager = LinearLayoutManager(this)
        checkIntent()
    }

    private fun checkIntent() {

        val intent = intent

        if (intent.getStringExtra("1") != null) {
            var url: String
            url = intent.getStringExtra("1")
            rssFeedPresenter.loadRssFeedForUrl(url)
        }
    }
}
