package com.example.rssfeedreader.ui.addNewFeedView

import android.os.Bundle
import com.example.rssfeedreader.R
import com.example.rssfeedreader.ui.base.BaseActivity

class AddNewFeedActivity : BaseActivity(), AddNewFeedContract.View {

    lateinit var addNewFeedPresenter: AddNewFeedContract.Presenter

    override fun setPresenter(presenter: AddNewFeedContract.Presenter) {
        addNewFeedPresenter = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_feed)

        setPresenter(AddNewFeedPresenter(this))
    }
}