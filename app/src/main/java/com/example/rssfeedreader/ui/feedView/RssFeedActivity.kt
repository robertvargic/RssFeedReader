package com.example.rssfeedreader.ui.feedView

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rssfeedreader.R
import com.example.rssfeedreader.ui.addNewFeedView.AddNewFeedActivity
import com.example.rssfeedreader.ui.base.BaseActivity
import kotlinx.android.synthetic.main.content_rss_feed.*
import me.toptas.rssconverter.RssFeed

class RssFeedActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, RssFeedContract.View {

    override fun initListView(feedItem: RssFeed) {
        val rssFeedAdapter = feedItem.items?.let { RssFeedAdapter(it, this) }
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
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
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

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_add_new_feed -> {
                System.out.println("ajbok")
                Toast.makeText(this, "started", Toast.LENGTH_LONG).show()
                val intent = Intent(this, AddNewFeedActivity::class.java).apply {
                }
                startActivity(intent)
            }
            R.id.nav_share -> {
                rssFeedPresenter.loadRssFeedForUrl("https://jalopnik.com/rss")
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
