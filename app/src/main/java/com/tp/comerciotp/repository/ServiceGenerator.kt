package com.tp.comerciotp.repository

import android.annotation.SuppressLint
import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tp.comerciotp.BuildConfig
import com.tp.comerciotp.application.AppConstants
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.net.ssl.HttpsURLConnection
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor


object ServiceGenerator {

    fun RetrofitEncrypt(): Retrofit {
        return retrofitBuilderEncrypt().client(httpBuilderEncrypt().build()).build()
    }

    fun retrofitBuilderEncrypt(): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL_TEST_DEV)
            .addConverterFactory(CustomGsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(getUnsafeOkHttpClient(httpBuilderEncrypt()))

    @SuppressLint("TrustAllX509TrustManager")
    private fun getUnsafeOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient {
        try {

            builder.hostnameVerifier { hostname, session ->
                if (!BuildConfig.DEBUG) {
                    val hostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier()
                    return@hostnameVerifier hostnameVerifier.verify(hostname, session)
                }
                true
            }

            return builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun httpBuilderEncrypt(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(RequestHeaderInterceptor())
            .addInterceptor(RequestEncryptInterceptor())
            .addInterceptor(
                LoggingInterceptor.Builder()
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .addHeader("version", BuildConfig.VERSION_NAME)
                    .request("Request")
                    .response("Response")
                    .log(Log.VERBOSE)
                    .build()
            )
    }



}