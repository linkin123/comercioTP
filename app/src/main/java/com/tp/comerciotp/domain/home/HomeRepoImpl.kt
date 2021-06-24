package com.tp.comerciotp.domain.home

import com.tp.comerciotp.data.model.QRRequest
import com.tp.comerciotp.data.remote.home.HomeDataSource

class HomeRepoImpl(private val dataSource: HomeDataSource) : HomeRepo{

    override suspend fun getQR(qrRequest: QRRequest) = dataSource.getQRStr(qrRequest)

}