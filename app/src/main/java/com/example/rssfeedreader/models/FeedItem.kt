package com.example.rssfeedreader.models

data class FeedItem(
    var title: String,
    var link: String,
    var pubDate: String,
    var description: String,
    var thumbnailUrl: String
) {
    constructor() : this("", "", "", "", "")
}