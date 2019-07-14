package com.robertvargic.cryptochecker.networking.base

 interface TaskListener<T> {
    fun onPreExecute()
    fun onSuccess(result: T)
    fun onError(error: Throwable)
}