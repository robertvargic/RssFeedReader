package com.example.rssfeedreader.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rssfeedreader.models.RssFeedUrl

@Database(entities = [RssFeedUrl::class], version = 1)
abstract class UrlDatabase : RoomDatabase() {
    abstract fun rssFeedDao(): RssFeedDao

    companion object {
        private var INSTANCE: UrlDatabase? = null

        fun getDatabaseInstance(context: Context?): UrlDatabase? {
            if (INSTANCE == null) {
                synchronized(UrlDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context!!.applicationContext,
                        UrlDatabase::class.java, "url-database")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyDatabaseInstance() {
            INSTANCE = null
        }
    }
}