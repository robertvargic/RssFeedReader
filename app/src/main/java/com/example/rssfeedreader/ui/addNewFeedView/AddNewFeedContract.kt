package com.example.rssfeedreader.ui.addNewFeedView

import com.example.rssfeedreader.ui.base.BasePresenter
import com.example.rssfeedreader.ui.base.BaseView

object AddNewFeedContract {

    interface View: BaseView<Presenter> {
    }

    interface Presenter : BasePresenter {
        fun addRssFeedToDatabase()
    }
}