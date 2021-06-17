package com.tp.comerciotp.repository

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RequestEncryptInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val updateRequest = originalRequest.newBuilder()
            .header("Content-Type", "text/plain")
            .header("Accept", "*/*")
            .build()
        return chain.proceed(updateRequest)
    }
}


