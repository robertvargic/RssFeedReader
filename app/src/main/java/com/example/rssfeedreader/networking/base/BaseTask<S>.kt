package com.robertvargic.cryptochecker.networking.base

import com.example.rssfeedreader.networking.RssService
import retrofit2.Retrofit

abstract class BaseTask(retrofit: Retrofit) {
    var mService: RssService = retrofit.create(RssService::class.java)

}