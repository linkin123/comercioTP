package com.tp.comerciotp.repository

object RetrofitClient {

    val webService by lazy {
        ServiceGenerator.RetrofitEncrypt().create(ApiServiceDefinition::class.java)
    }

}