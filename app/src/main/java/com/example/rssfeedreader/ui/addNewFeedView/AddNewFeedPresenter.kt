package com.example.rssfeedreader.ui.addNewFeedView

import kotlinx.coroutines.async
import com.example.rssfeedreader.database.UrlDatabase
import com.example.rssfeedreader.database.RssFeedDao
import com.example.rssfeedreader.models.RssFeedUrl


class AddNewFeedPresenter : AddNewFeedContract.Presenter {

    lateinit var addNewFeedView: AddNewFeedContract.View

    override fun deleteUser(rssItem: String, database: UrlDatabase) {
        database.rssFeedDao().deleteRssFeedItem(rssItem)
        getRssFeedsFromDatabase(database)
    }

    constructor(addNewFeedView: AddNewFeedContract.View) {
        this.addNewFeedView = addNewFeedView
    }

    override fun addRssFeedToDatabase(database: UrlDatabase, rrsFeedUrl: RssFeedUrl) {
        database.rssFeedDao().insert(rrsFeedUrl)
        getRssFeedsFromDatabase(database)
    }

    override fun getRssFeedsFromDatabase(database: UrlDatabase) {
        val urlList = database.rssFeedDao().getAll()
        addNewFeedView.initListView(urlList)
    }

    override fun start() {
    }
}