package com.example.rssfeedreader.ui.base

interface BaseView<in T> {
    fun setPresenter(presenter: T)
}