package com.robertvargic.cryptochecker.networking.base

interface ServerTask<R> {
    fun execute(listener: TaskListener<R>)
}