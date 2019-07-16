package com.example.rssfeedreader.ui.addNewFeedView

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rssfeedreader.R
import com.example.rssfeedreader.database.UrlDatabase
import com.example.rssfeedreader.models.RssFeedUrl
import com.example.rssfeedreader.ui.base.BaseActivity
import com.example.rssfeedreader.ui.feedView.RssFeedActivity
import kotlinx.android.synthetic.main.activity_add_new_feed.*

class AddNewFeedActivity : BaseActivity(), AddNewFeedContract.View {

    lateinit var addNewFeedPresenter: AddNewFeedContract.Presenter

    override fun initListView(feedItems: List<RssFeedUrl>) {
        var context = this
        val listener = object : AddNewFeedAdapter.OnRssFeedClickListener {
            override fun onDelete(rssItem: String) {
                addNewFeedPresenter.deleteUser(rssItem, UrlDatabase.getDatabaseInstance(context)!!)
            }

            override fun onClick(url: String) {
                val intent = Intent()
                intent.setClass(context, RssFeedActivity::class.java)
                intent.putExtra("1", url)
                startActivity(intent)
            }
        }
        var addNewFeedAdapter = AddNewFeedAdapter(feedItems, this, listener)
        activity_add_new_feed_recycler_view.adapter = addNewFeedAdapter
        addNewFeedAdapter.notifyDataSetChanged()
    }

    override fun setPresenter(presenter: AddNewFeedContract.Presenter) {
        addNewFeedPresenter = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_feed)

        setPresenter(AddNewFeedPresenter(this))
        initListeners()

        activity_add_new_feed_recycler_view.layoutManager = LinearLayoutManager(this)
        addNewFeedPresenter.getRssFeedsFromDatabase(UrlDatabase.getDatabaseInstance(this)!!)
    }

    private fun initListeners() {
        activity_add_new_feed_button.setOnClickListener {
            if (isNetworkConnected()) {
                val rssFeedUrl = RssFeedUrl(activity_add_new_feed_edit_text.text.toString())
                addNewFeedPresenter.addRssFeedToDatabase(UrlDatabase.getDatabaseInstance(this)!!, rssFeedUrl)
                activity_add_new_feed_edit_text.clearComposingText()
            } else {
                Toast.makeText(this, getString(R.string.no_internet_connection), Toast.LENGTH_LONG).show()
            }
        }
    }
}