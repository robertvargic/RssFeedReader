package com.example.rssfeedreader.ui.addNewFeedView

import androidx.room.Database
import com.example.rssfeedreader.database.UrlDatabase
import com.example.rssfeedreader.models.RssFeedUrl
import com.example.rssfeedreader.ui.base.BasePresenter
import com.example.rssfeedreader.ui.base.BaseView

object AddNewFeedContract {

    interface View: BaseView<Presenter> {
        fun initListView(feedItems: List<RssFeedUrl>)
    }

    interface Presenter : BasePresenter {
        fun addRssFeedToDatabase(database: UrlDatabase, rrsFeedUrl: RssFeedUrl)
        fun getRssFeedsFromDatabase(database: UrlDatabase)
        fun deleteUser(rssItem: String, database: UrlDatabase)
    }
}