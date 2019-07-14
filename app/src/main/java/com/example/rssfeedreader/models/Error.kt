package com.example.rssfeedreader.models

data class Error(val code: Int = 0,
                 var message: String = "") {
    companion object {
        const val ERROR_GENERIC = -1
        const val ERROR_NETWORK = -2

        fun generic() = Error(code = ERROR_GENERIC)

        fun network() = Error(code = ERROR_NETWORK)
    }

}