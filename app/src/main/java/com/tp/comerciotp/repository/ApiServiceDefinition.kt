package com.tp.comerciotp.repository

import com.tp.comerciotp.data.model.QRRequest
import com.tp.comerciotp.data.model.QRResponse
import com.tp.comerciotp.repository.Apis.GENERATED_QR
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServiceDefinition {

    @POST(GENERATED_QR)
    suspend fun getQRtoBAZ(@Body QRrequest : QRRequest): QRResponse

}