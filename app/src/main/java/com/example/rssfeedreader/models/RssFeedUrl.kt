package com.example.rssfeedreader.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "urlTable")
data class RssFeedUrl(
    @PrimaryKey @ColumnInfo(name = "url") val url: String
)