package com.example.rssfeedreader.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.rssfeedreader.models.RssFeedUrl

@Dao
interface RssFeedDao {

    @Insert
    fun insert(rssFeedUrl: RssFeedUrl)

    @Query("SELECT * FROM urlTable")
    fun getAll(): List<RssFeedUrl>

    @Query("DELETE from urlTable WHERE id = :userId")
    fun deleteUser(rssFeedUrl: String)
}