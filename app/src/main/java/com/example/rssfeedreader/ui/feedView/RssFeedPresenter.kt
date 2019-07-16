package com.example.rssfeedreader.ui.feedView

import android.widget.Toast
import com.example.rssfeedreader.database.UrlDatabase
import com.example.rssfeedreader.models.RssFeedUrl
import com.example.rssfeedreader.networking.RetrofitUtil
import com.example.rssfeedreader.networking.tasks.GetRssFeedTask
import com.robertvargic.cryptochecker.networking.base.TaskListener
import me.toptas.rssconverter.RssFeed
import me.toptas.rssconverter.RssItem


class RssFeedPresenter : RssFeedContract.Presenter {

    lateinit var rssFeedView: RssFeedContract.View

    constructor(rssFeedView: RssFeedContract.View) {
        this.rssFeedView = rssFeedView
    }

    override fun loadRssFeedForUrl(url: String) {
//        var shit = FeedItem("title", "aa", "aa", "aa", "aa")
//        var feedItems = mutableListOf (shit)
//        rssFeedView.initListView(feedItems)
        val getRssFeedTask = GetRssFeedTask(RetrofitUtil().createRetrofitForUrl(), url)
        getRssFeedTask.execute(object: TaskListener<RssFeed>{
            override fun onPreExecute() {
            }

            override fun onSuccess(result: RssFeed) {
                rssFeedView.initListView(result)
            }

            override fun onError(error: Throwable) {
                System.out.println(error.toString())
            }

        })
    }

    override fun start() {
    }
}