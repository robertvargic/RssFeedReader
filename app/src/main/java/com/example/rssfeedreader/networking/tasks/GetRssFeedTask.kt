package com.example.rssfeedreader.networking.tasks

import com.robertvargic.cryptochecker.networking.base.BaseTask
import com.robertvargic.cryptochecker.networking.base.ServerTask
import com.robertvargic.cryptochecker.networking.base.TaskListener
import me.toptas.rssconverter.RssFeed
import retrofit2.*

class GetRssFeedTask(retrofit: Retrofit, private val url: String) : BaseTask(retrofit), ServerTask<RssFeed> {

    override fun execute(listener: TaskListener<RssFeed>) {
        listener.onPreExecute()
        val call = mService.getRssFeed(url)

        call.enqueue(object : Callback<RssFeed> {
            override fun onFailure(call: Call<RssFeed>?, t: Throwable?) {
                t?.run { listener.onError(this) }
            }

            override fun onResponse(call: Call<RssFeed>?, response: Response<RssFeed>?) {
                response?.body()?.run { listener.onSuccess(this) }
                response?.errorBody()?.run { listener.onError(HttpException(response)) }
            }
        })
    }
}