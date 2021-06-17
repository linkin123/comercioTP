package com.tp.comerciotp.domain.home

import com.tp.comerciotp.data.model.QRRequest
import com.tp.comerciotp.data.model.QRResponse

interface HomeRepo {
    suspend fun getQR(qrRequest: QRRequest):QRResponse

}