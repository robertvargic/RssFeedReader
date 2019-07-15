package com.example.rssfeedreader.base

import android.app.Application
import androidx.room.Room
import com.example.rssfeedreader.database.UrlDatabase

class RssFeedReader : Application() {
    lateinit var database: UrlDatabase
    override fun onCreate() {
        super.onCreate()
        database = UrlDatabase.getDatabaseInstance(this)!!
    }
}