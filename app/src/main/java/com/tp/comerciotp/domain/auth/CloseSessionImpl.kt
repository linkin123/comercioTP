package com.tp.comerciotp.domain.auth

import com.tp.comerciotp.data.remote.auth.CloseSessionDataSource

class CloseSessionImpl(private val dataSource: CloseSessionDataSource) : CloseSessionRepo {
    override fun singOut(): Boolean = dataSource.singOut()

}