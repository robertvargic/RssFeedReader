package com.example.rssfeedreader.ui.feedView

import com.example.rssfeedreader.models.FeedItem
import com.example.rssfeedreader.ui.base.BasePresenter
import com.example.rssfeedreader.ui.base.BaseView

object RssFeedContract {
    interface View: BaseView<Presenter> {
        fun initListView(feedItem: MutableList<FeedItem>)
    }

    interface Presenter : BasePresenter {
        fun loadRssFeedForUrl(url: String)
    }
}