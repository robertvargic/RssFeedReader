package com.example.rssfeedreader.ui.feedView

import com.example.rssfeedreader.ui.base.BasePresenter
import com.example.rssfeedreader.ui.base.BaseView
import me.toptas.rssconverter.RssFeed

object RssFeedContract {
    interface View: BaseView<Presenter> {
        fun initListView(feedItem: RssFeed)
    }

    interface Presenter : BasePresenter {
        fun loadRssFeedForUrl(url: String)
    }
}