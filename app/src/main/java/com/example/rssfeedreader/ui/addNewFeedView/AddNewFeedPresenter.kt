package com.example.rssfeedreader.ui.addNewFeedView

class AddNewFeedPresenter: AddNewFeedContract.Presenter {

    lateinit var addNewFeedView: AddNewFeedContract.View

    constructor(addNewFeedView: AddNewFeedContract.View) {
        this.addNewFeedView = addNewFeedView
    }

    override fun addRssFeedToDatabase() {

    }

    override fun start() {
    }
}