package com.tp.comerciotp.data.remote.home

import com.tp.comerciotp.data.model.QRRequest
import com.tp.comerciotp.repository.ApiServiceDefinition

class HomeDataSource(private val webService : ApiServiceDefinition) {

    suspend fun getQRStr(qrRequest : QRRequest) = webService.getQRtoBAZ(qrRequest)

}