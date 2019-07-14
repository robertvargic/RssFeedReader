package com.example.rssfeedreader.networking

import com.google.gson.*
import me.toptas.rssconverter.RssConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class RetrofitUtil {

//    private val DATE_JSON_SERIALIZER = JsonSerializer<Date> { src, typeOfSrc, context -> if (src == null) null else JsonPrimitive(src.time) }
//    private val DATE_JSON_DESERIALIZER = JsonDeserializer<Date> { json, typeOfT, context -> if (json == null) null else Date(json.asLong) }

    fun createRetrofitForUrl(): Retrofit {
        return createRetrofit()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://example.com")
            .addConverterFactory(RssConverterFactory.create())
            .client(okHttpClient())
            .build()
    }

//    private fun getGson(): Gson {
//        return getCommonBuilder()
//                .create()
//    }

//    private fun getCommonBuilder(): GsonBuilder {
//        return GsonBuilder()
//                .registerTypeAdapter(Date::class.java, DATE_JSON_SERIALIZER)
//                .registerTypeAdapter(Date::class.java, DATE_JSON_DESERIALIZER)
//    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private fun okHttpClient(): OkHttpClient =
        OkHttpClient.Builder().apply {
            addInterceptor(provideLoggingInterceptor())
        }.build()
}