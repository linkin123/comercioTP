package com.tp.comerciotp.repository

import android.util.Log
import com.tp.comerciotp.utils.AESCryptoUtils
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset

class RequestEncryptInterceptor : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val body = request.body
        val buffer = Buffer()
        if (body != null) {
            body.writeTo(buffer)
            var charset = Charset.forName("UTF-8")
            val contentType = body.contentType()
            if (contentType != null) {
                charset = contentType.charset(charset)
            }
            var paramsStr = buffer.readString(charset!!)
            try {
                paramsStr = AESCryptoUtils.encrypt(paramsStr)
            } catch (e: Exception) {
                Log.e("TAG", "error")
            }
            val requestBody = RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                paramsStr
            )
            request = request.newBuilder()
                .addHeader("Content-Type", "text/plain")
                .post(requestBody)
                .build()
        }
        return chain.proceed(request)
    }

}


