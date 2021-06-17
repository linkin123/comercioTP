package com.tp.comerciotp.data.remoteeeee.home

import com.google.firebase.auth.FirebaseAuth
import com.tp.comerciotp.data.model.QRRequest
import com.tp.comerciotp.repository.ApiServiceDefinition

class HomeDataSource(private val webService : ApiServiceDefinition) {

    suspend fun getQRStr(qrRequest : QRRequest) = webService.getQRtoBAZ(qrRequest)

}