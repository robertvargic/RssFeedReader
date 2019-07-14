package com.example.rssfeedreader.networking

import me.toptas.rssconverter.RssFeed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RssService {
    @GET("")
    fun getRssFeed(@Url url: String): Call<RssFeed>
}